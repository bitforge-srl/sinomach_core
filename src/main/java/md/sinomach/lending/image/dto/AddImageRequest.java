package md.sinomach.lending.image.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AddImageRequest {
private Long id;
private MultipartFile bytes;
private String nameFile;
}
