package md.sinomach.lending.productPage;

import lombok.Builder;
import lombok.Data;
import md.sinomach.lending.product.Feature;
import md.sinomach.lending.subtypes.SubType;
import md.sinomach.lending.type.Type;

import java.util.List;

@Data
@Builder
public class ProductPageResponse {
    private boolean success;
    private String error;

    private Long id;
    private String name;
    private Type type;
    private SubType subType;
    private String fullDescription;
    private String shortSpecification;
    private String content;
    private String img;
    private List<Feature> features;
    private String additionalDescription;

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
