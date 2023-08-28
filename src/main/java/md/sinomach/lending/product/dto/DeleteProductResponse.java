package md.sinomach.lending.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteProductResponse {

    private boolean success;
    private Error error;

    public static DeleteProductResponse success() {
        return DeleteProductResponse.builder()
                .success(true)
                .error(Error.ok)
                .build();
    }

    public static DeleteProductResponse failed(Error error) {
        return DeleteProductResponse.builder()
                .success(false)
                .error(error)
                .build();
    }

    public enum Error {
        ok,
        failed,
        non_existent_product
    }

}
