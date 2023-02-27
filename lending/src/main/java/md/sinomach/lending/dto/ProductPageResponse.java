package md.sinomach.lending.dto;

import lombok.Builder;
import lombok.Data;
import md.sinomach.lending.dao.Feature;
import md.sinomach.lending.dao.SubType;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class ProductPageResponse {
    private boolean success;
    private String error;

    private Long id;
    private String name;
    private String fullDescription;
    private String shortDescription;
    private String shortSpecification;
    private String fullSpecification;
    private String videoUrl;
    private List<Feature> features;
    private SubType subType;
    private Set<ProductInfo> similarProducts;

    public static ProductPageResponseBuilder success(){
      return  ProductPageResponse.builder()
                .success(true)
                .error("ok");
    }

    public static ProductPageResponseBuilder failed(String errorMessage){
        return  ProductPageResponse.builder()
                .success(false)
                .error(errorMessage);
    }

}
