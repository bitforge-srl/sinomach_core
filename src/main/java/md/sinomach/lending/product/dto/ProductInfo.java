package md.sinomach.lending.product.dto;

import lombok.Builder;
import lombok.Data;
import md.sinomach.lending.type.Type;

@Data
@Builder
public class ProductInfo {
    private String name;
    private Long id;
    private Type type;
    private String img;
    private String shortSpecification;
}
