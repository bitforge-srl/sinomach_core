package md.sinomach.lending.product.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class AddProductResponse {

        private boolean success;
        private Error error;

        public static AddProductResponse success() {
            return AddProductResponse.builder()
                    .success(true)
                    .error(Error.ok)
                    .build();
        }

        public static AddProductResponse failed(Error error) {
            return AddProductResponse.builder()
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

