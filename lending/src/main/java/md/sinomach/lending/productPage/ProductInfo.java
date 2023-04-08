package md.sinomach.lending.productPage;

import lombok.Builder;
import lombok.Data;
import md.sinomach.lending.menuProduct.Type;

@Data
@Builder
public class ProductInfo {
    private String name;
    private Long id;
    private Type type;
    private String img;
    private String shortSpecification;
}
