package md.sinomach.lending.subtypes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditSubTypeRequest {
    public Long subtypeId;
    public String editedNameSubType;
    public Long typeId;
}
