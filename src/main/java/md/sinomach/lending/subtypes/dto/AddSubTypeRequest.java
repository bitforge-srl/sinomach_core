package md.sinomach.lending.subtypes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddSubTypeRequest {
    private Long typeId;
    private String name;

}
