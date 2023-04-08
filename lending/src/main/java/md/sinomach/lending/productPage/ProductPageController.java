package md.sinomach.lending.productPage;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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

        return getBuild(id, product);
    }

    private ProductPageResponse getBuild(Long id, Product product) {
        return ProductPageResponse.success()
                .id(id)
                .name(product.getName())
                .fullDescription(product.getFullDescription())
                .content(product.getContent())
                .shortSpecification(product.getShortSpecification())
                .additionalDescription(product.getAdditionalDescription())
                .features(product.getFeatures())
                .type(product.getSubType().getType())
                .img(product.getImg())
                .build();
    }


}

