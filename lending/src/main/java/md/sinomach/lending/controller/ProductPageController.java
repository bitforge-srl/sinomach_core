package md.sinomach.lending.controller;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.Product;
import md.sinomach.lending.dao.SubType;
import md.sinomach.lending.dto.ProductInfo;
import md.sinomach.lending.dto.ProductPage;
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
    public ProductPage getProduct(@PathVariable("id") Long id) {
        Optional productByID = productService.getProductByID(id);
        if (productByID.isEmpty()) {
            return ProductPage.builder()
                    .name("no fund product")
                    .build();
        } else {
            Product product = (Product) productByID.get();
            Optional<SubType> subType = Optional.ofNullable(product.getSubType());
            if (subType.isEmpty()) {
                return ProductPage.builder()
                        .id(id)
                        .name(product.getName())
                        .shortDescription(product.getShortDescription())
                        .fullDescription(product.getFullDescription())
                        .fullSpecification(product.getFullSpecification())
                        .shortSpecification(product.getShortSpecification())
                        .videoUrl(product.getVideoUrl())
                        .features(product.getFeatures())
                        .build();
            } else {
                Set<ProductInfo> productsBySubType = subType.get().getProducts().stream()
                        .map(this::convertProduct)
                        .collect(Collectors.toSet());

                return ProductPage.builder()
                        .id(id)
                        .name(product.getName())
                        .shortDescription(product.getShortDescription())
                        .fullDescription(product.getFullDescription())
                        .fullSpecification(product.getFullSpecification())
                        .shortSpecification(product.getShortSpecification())
                        .videoUrl(product.getVideoUrl())
                        .features(product.getFeatures())
                        .productsBySubType(productsBySubType)
                        .build();
            }


        }
    }

    private ProductInfo convertProduct(Product product) {
        return ProductInfo.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }
}

