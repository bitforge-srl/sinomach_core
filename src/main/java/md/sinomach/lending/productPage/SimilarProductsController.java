package md.sinomach.lending.productPage;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.product.Product;
import md.sinomach.lending.product.ProductService;
import md.sinomach.lending.product.dto.ProductInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/similar_product")
@RequiredArgsConstructor
public class SimilarProductsController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public SimilarProductsResponse getSimilarProducts(@PathVariable("id") Long id) {
        Optional<Product> productByID = productService.getProductByID(id);

        if (productByID.isEmpty()) {
            return SimilarProductsResponse
                    .failed("Product not found")
                    .build();
        }

        List<Product> similarProductsByProduct = productService.getSimilarProductsByProduct(productByID.get());

        Set<ProductInfo> productInfoSet = similarProductsByProduct.stream()
                .map(this::convertProduct)
                .collect(Collectors.toSet());

        return getBuild(id, productInfoSet);
    }

    private ProductInfo convertProduct(Product product) {
        return ProductInfo.builder()
                .id(product.getId())
                .name(product.getName())
                .img(product.getImg())
                .shortSpecification(product.getShortSpecification())
                .type(product.getSubType().getType())
                .build();
    }

    private SimilarProductsResponse getBuild(Long id, Set<ProductInfo> productInfoSet) {
        return SimilarProductsResponse.success()
                .similarProductsInfo(productInfoSet)
                .build();
    }
}
