package md.sinomach.lending.image.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class GetImageResponse {
    private boolean success;
    private Error error;
    private Image image;

    public static GetImageResponse success(Image image) {
        return GetImageResponse.builder()
                .success(true)
                .error(Error.ok)
                .image(image)
                .build();
    }

    public static GetImageResponse failed(Error error) {
        return GetImageResponse.builder()
                .success(false)
                .error(Error.failed)
                .build();
    }

    public enum Error {
        ok,
        failed,
    }

}
