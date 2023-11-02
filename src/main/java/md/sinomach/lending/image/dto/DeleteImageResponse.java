package md.sinomach.lending.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteImageResponse {
    private boolean success;
    private Error error;
    private Long id;

    public static DeleteImageResponse success(Long imgId) {
        return DeleteImageResponse.builder()
                .success(true)
                .error(DeleteImageResponse.Error.ok)
                .id(imgId)
                .build();
    }
    public static DeleteImageResponse failed(DeleteImageResponse.Error error){
        return DeleteImageResponse.builder()
                .success(false)
                .error(DeleteImageResponse.Error.failed)
                .build();
    }
    public enum Error {
        ok,
        failed,
    }
}
