package md.sinomach.lending.menuProduct;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.product.Product;
import md.sinomach.lending.product.dto.ProductInfo;
import md.sinomach.lending.subtypes.SubType;
import md.sinomach.lending.type.Type;
import md.sinomach.lending.type.TypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/menu_product")
@RequiredArgsConstructor
public class MenuProductController {
    private final md.sinomach.lending.product.ProductService ProductService;
    private final TypeService typeService;

    @GetMapping("all")
    public Set<TypeMenuInfo> menuProducts() {

        List<Type> allTypes = typeService.getAllTypes();

        return allTypes.stream()
                .map(MenuProductController::convertType)
                .collect(Collectors.toSet());
    }

    private static ProductInfo convertProduct(Product product) {
        return ProductInfo.builder()
                .name(product.getName())
                .id(product.getId())
                .img(product.getImg())
                .shortSpecification(product.getShortSpecification())
                .build();
    }

    private static SubTypeMenuInfo convertSubType(SubType subType) {
        Set<ProductInfo> products = subType.getProducts().stream()
                .map(MenuProductController::convertProduct)
                .collect(Collectors.toSet());

        return SubTypeMenuInfo.builder()
                .name(subType.getName())
                .products(products)
                .build();
    }

    private static TypeMenuInfo convertType(Type type) {
        Set<SubTypeMenuInfo> subTypes = type.getSubTypes().stream()
                .map(MenuProductController::convertSubType)
                .collect(Collectors.toSet());

        return TypeMenuInfo.builder()
                .id(type.getId())
                .name(type.getName())
                .subTypes(subTypes)
                .build();
    }
}

