package md.sinomach.lending.controller;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.Product;
import md.sinomach.lending.dao.SubType;
import md.sinomach.lending.dto.ProductDto;
import md.sinomach.lending.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Set<ProductDto> getAll(){
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
}
