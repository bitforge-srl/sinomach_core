package md.sinomach.lending.type.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditTypeNameResponse {

    private boolean success;
    private EditTypeNameResponse.Error error;

    public static EditTypeNameResponse success() {
        return EditTypeNameResponse.builder()
                .success(true)
                .error(EditTypeNameResponse.Error.ok)
                .build();
    }

    public static EditTypeNameResponse failed(EditTypeNameResponse.Error error) {
        return EditTypeNameResponse.builder()
                .success(false)
                .error(error)
                .build();
    }

    public  enum Error{
        ok,
        alreadyExist,
        type_not_found
    }
}
