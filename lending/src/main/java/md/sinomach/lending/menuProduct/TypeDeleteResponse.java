package md.sinomach.lending.menuProduct;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeDeleteResponse {

    private boolean success;
    private String error;

    public static TypeDeleteResponse success() {
        return TypeDeleteResponse.builder()
                .success(true)
                .error("type is deleted")
                .build();
    }

    public static TypeDeleteResponse failed(String errorMessage) {
        return TypeDeleteResponse.builder()
                .success(false)
                .error(errorMessage)
                .build();
    }
}
