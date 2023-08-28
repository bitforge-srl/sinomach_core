package md.sinomach.lending.product;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.product.dto.*;
import md.sinomach.lending.subtypes.SubType;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("all")
    public Set<ProductDto> getAll() {
        Set<Product> allDaoProducts = productService.getAllProducts();

        return allDaoProducts.stream()
                .map(dao -> {
                    List<ProductDto.Feature> collect = dao.getFeatures().stream()
                            .map(daoFeature -> ProductDto.Feature.builder()
                                    .description(daoFeature.getDescription())
                                    .imageURL(daoFeature.getImageUrl())
                                    .build())
                            .collect(Collectors.toList());

                    return ProductDto.builder()
                            .name(dao.getName())
                            .fullDescription(dao.getFullDescription())
                            .content(dao.getContent())
                            .subType(Optional.ofNullable(dao.getSubType()).orElse(SubType.builder().build()).getName())
                            .additionalDescription(dao.getAdditionalDescription())
                            .shortSpecifications(dao.getShortSpecification())
                            .features(collect)
                            .build();
                })
                .collect(Collectors.toSet());
    }

    @PostMapping("/add")
    public AddProductResponse addProduct(@RequestBody @NotNull AddProductRequest addProductRequest) {

        Product product = new Product();

        product.setName(addProductRequest.getName());
        product.setFullDescription(addProductRequest.getFullDescription());
        product.setShortSpecification(addProductRequest.getShortSpecification());
        product.setContent(addProductRequest.getContent());
        product.setAdditionalDescription(addProductRequest.getAdditionalDescription());
        product.setImg(addProductRequest.getImg());
        product.setSubType(addProductRequest.getSubType());
        product.setFeatures(new ArrayList<>());

        return productService.addProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public DeleteProductResponse removeProduct(@PathVariable("id") Long id) {

        return productService.removeProduct(id);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) {

        return productService.getProductByID(id).get();
    }

    @PostMapping("/edit")
    public EditProductResponse editProduct(@RequestBody EditProductRequest editProduct){

        return productService.editProduct(editProduct);
    }
}
