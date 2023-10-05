package md.sinomach.lending.image.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class GetImageRequest {
    private Long id;
}
