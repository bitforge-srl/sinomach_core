package md.sinomach.lending.type.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RemoveTypeResponse {

    private boolean success;
    private RemoveTypeResponse.Error error;

    public static RemoveTypeResponse success() {
        return RemoveTypeResponse.builder()
                .success(true)
                .error(RemoveTypeResponse.Error.ok)
                .build();
    }

    public static RemoveTypeResponse failed(RemoveTypeResponse.Error error) {
        return RemoveTypeResponse.builder()
                .success(false)
                .error(error)
                .build();
    }

    public  enum Error{
        ok,
        notFound
    }
}
