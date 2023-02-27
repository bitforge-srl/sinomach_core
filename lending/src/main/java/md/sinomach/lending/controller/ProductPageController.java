package md.sinomach.lending.controller;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.Product;
import md.sinomach.lending.dto.ProductInfo;
import md.sinomach.lending.dto.ProductPageResponse;
import md.sinomach.lending.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/product_page")
@RequiredArgsConstructor
public class ProductPageController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ProductPageResponse getProduct(@PathVariable("id") Long id) {
        Optional<Product> productByID = productService.getProductByID(id);

        if (productByID.isEmpty()) {
            return ProductPageResponse
                    .failed("Product not found")
                    .build();
        }

        Product product = productByID.get();

        Set<ProductInfo> similarProducts = productService.getSimilarProductsByProduct(product).stream()
                .map(this::convertProduct)
                .collect(Collectors.toSet());

        ProductPageResponse productPageResponse = getBuild(id, product);
        productPageResponse.setSimilarProducts(similarProducts);

        return productPageResponse;
    }

    private ProductPageResponse getBuild(Long id, Product product) {
        return ProductPageResponse.success()
                .id(id)
                .name(product.getName())
                .shortDescription(product.getShortDescription())
                .fullDescription(product.getFullDescription())
                .fullSpecification(product.getFullSpecification())
                .shortSpecification(product.getShortSpecification())
                .videoUrl(product.getVideoUrl())
                .features(product.getFeatures())
                .build();
    }

    private ProductInfo convertProduct(Product product) {
        return ProductInfo.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }
}

