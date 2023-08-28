package md.sinomach.lending.subtypes.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class AddSubTypeResponse {

        private boolean success;
        private Error error;

        public static AddSubTypeResponse success() {
            return AddSubTypeResponse.builder()
                    .success(true)
                    .error(AddSubTypeResponse.Error.ok)
                    .build();
        }

        public static AddSubTypeResponse failed(AddSubTypeResponse.Error error) {
            return AddSubTypeResponse.builder()
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

