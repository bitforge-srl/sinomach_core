package md.sinomach.lending.product.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDto {
    private String name;
    private String subType;
    private String fullDescription;
    private String shortSpecifications;
    private String content;
    private String img;
    private String additionalDescription;
    private List<Feature> features;

    @Data
    @Builder
    public static class Feature {
        private String imageURL;
        private String description;
    }

}
