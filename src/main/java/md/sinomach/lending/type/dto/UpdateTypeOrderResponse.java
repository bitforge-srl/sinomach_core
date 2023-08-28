package md.sinomach.lending.type.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTypeOrderResponse {

    public boolean success;
    public UpdateTypeOrderResponse.Error error;

    public static UpdateTypeOrderResponse success() {
        return UpdateTypeOrderResponse.builder()
                .success(true)
                .error(UpdateTypeOrderResponse.Error.ok)
                .build();
    }

    public static UpdateTypeOrderResponse failed(UpdateTypeOrderResponse.Error error) {
        return UpdateTypeOrderResponse.builder()
                .success(false)
                .error(error)
                .build();
    }
    public  enum Error{
        ok,
        failed
    }
}
