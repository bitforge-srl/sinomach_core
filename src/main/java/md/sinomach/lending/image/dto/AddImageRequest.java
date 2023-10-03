package md.sinomach.lending.image.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddImageRequest {
private Long id;
private byte[] bytes;
private String nameFile;
}
