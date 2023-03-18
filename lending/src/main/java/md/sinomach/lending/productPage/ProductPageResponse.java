package md.sinomach.lending.productPage;

import lombok.Builder;
import lombok.Data;
import md.sinomach.lending.menuProduct.SubType;

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
    private String shortSpecification;
    private String content;
    private List<Feature> features;
    private SubType subType;
    private Set<ProductInfo> similarProducts;
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
