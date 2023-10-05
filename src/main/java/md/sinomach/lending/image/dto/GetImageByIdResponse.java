package md.sinomach.lending.image.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.MediaType;

@Data
@Builder
public class GetImageByIdResponse {
    private boolean success;
    private Error error;
    private byte[] bytes;

    public static byte[] success(byte[] bytes) {
        return bytes;
    }

    public static GetImageByIdResponse failed(Error error) {
        return GetImageByIdResponse.builder()
                .success(false)
                .error(Error.failed)
                .build();
    }

    public enum Error {
        ok,
        failed,
    }

}
