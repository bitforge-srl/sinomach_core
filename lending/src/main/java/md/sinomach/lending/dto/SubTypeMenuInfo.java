package md.sinomach.lending.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SubTypeMenuInfo {
    private String name;
    private Set<ProductInfo> products;
}
