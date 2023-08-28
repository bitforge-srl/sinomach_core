package md.sinomach.lending.type.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddTypeResponse {

    private boolean success;
    private Error error;

    public static AddTypeResponse success() {
        return AddTypeResponse.builder()
                .success(true)
                .error(Error.ok)
                .build();
    }

    public static AddTypeResponse failed(Error error) {
        return AddTypeResponse.builder()
                .success(false)
                .error(error)
                .build();
    }

    public  enum Error{
        ok,
        alreadyExist
    }
}
