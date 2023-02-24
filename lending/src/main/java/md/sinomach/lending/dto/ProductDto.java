package md.sinomach.lending.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductDto {
    private String subType;
    private String name;
    private String fullDescription;
    private String shortDescription;
    private String shortSpecifications;
    private String fullSpecifications;
    private String videoURLs;
    private List<Feature> features;

    @Data
    @Builder
    public static class Feature {
        private String imageURL;
        private String description;
    }

}
