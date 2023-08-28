package md.sinomach.lending.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditProductResponse {
    private boolean success;
    private Error error;

    public static EditProductResponse success() {
        return EditProductResponse.builder()
                .success(true)
                .error(Error.ok)
                .build();
    }

    public static EditProductResponse failed(Error error) {
        return EditProductResponse.builder()
                .success(false)
                .error(error)
                .build();
    }

    public  enum Error{
        ok,
        failed,
        alreadyExist
    }
}
