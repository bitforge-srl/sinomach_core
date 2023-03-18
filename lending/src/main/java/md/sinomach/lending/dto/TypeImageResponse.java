package md.sinomach.lending.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeImageResponse {
    private String type;
    private String imageSource;
}
