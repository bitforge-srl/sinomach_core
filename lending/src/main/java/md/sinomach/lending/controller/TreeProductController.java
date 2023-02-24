package md.sinomach.lending.controller;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.Product;
import md.sinomach.lending.dao.SubType;
import md.sinomach.lending.dao.Type;
import md.sinomach.lending.dto.ProductDto;
import md.sinomach.lending.service.ProductService;
import md.sinomach.lending.service.TreeProductService;
import md.sinomach.lending.service.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/tree_product")
@RequiredArgsConstructor
public class TreeProductController {
    private final ProductService productService;

    private final TreeProductService treeProductService;
    private final TypeService typeService;

    @GetMapping("all")
    public Set<ProductDto> allTreeProduct() {

        Set<Type> allTypes = typeService.getAllTypes();

        Set<Product> allDaoProducts = treeProductService.getAllProducts();

        return allDaoProducts.stream()
                .map(dao -> {
                    return ProductDto.builder()
                            .name(dao.getName())
                            .subType(Optional.ofNullable(dao.getSubType()).orElse(SubType.builder().build()).getName())
                            .build();
                })
                .collect(Collectors.toSet());
    }
}
