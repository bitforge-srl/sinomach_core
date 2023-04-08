package md.sinomach.lending.productPage;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class SimilarProductsResponse {
    private boolean success;
    private String error;

    private Set<ProductInfo> similarProductsInfo;


    public static SimilarProductsResponseBuilder success(){
        return  SimilarProductsResponse.builder()
                .success(true)
                .error("ok");
    }

    public static SimilarProductsResponseBuilder failed(String errorMessage){
        return  SimilarProductsResponse.builder()
                .success(false)
                .error(errorMessage);
    }
}
