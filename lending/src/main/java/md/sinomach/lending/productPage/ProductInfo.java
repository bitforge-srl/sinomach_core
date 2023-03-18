package md.sinomach.lending.productPage;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfo {
    private String name;
    private Long id;
}
