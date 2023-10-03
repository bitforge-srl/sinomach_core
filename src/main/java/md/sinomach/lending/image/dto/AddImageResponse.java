package md.sinomach.lending.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddImageResponse {
    private boolean success;
    private Error error;
    private Long id;

    public static AddImageResponse success() {
        return AddImageResponse.builder()
                .success(true)
                .error(Error.ok)
                .build();
    }
    public static AddImageResponse failed(Error error){
        return AddImageResponse.builder()
                .success(false)
                .error(Error.failed)
                .build();
    }

    public enum Error {
        ok,
        failed,
    }

}
