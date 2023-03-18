package md.sinomach.lending.menuProduct;

import lombok.Builder;
import lombok.Data;
import md.sinomach.lending.productPage.ProductInfo;

import java.util.Set;

@Data
@Builder
public class SubTypeMenuInfo {
    private String name;
    private Set<ProductInfo> products;
}
