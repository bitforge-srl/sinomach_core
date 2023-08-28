package md.sinomach.lending.subtypes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditSudTypeResponse {
    private boolean success;
    private EditSudTypeResponse.Error error;

    public static EditSudTypeResponse success() {
        return EditSudTypeResponse.builder()
                .success(true)
                .error(EditSudTypeResponse.Error.ok)
                .build();
    }

    public static EditSudTypeResponse failed(EditSudTypeResponse.Error error) {
        return EditSudTypeResponse.builder()
                .success(false)
                .error(error)
                .build();
    }

    public  enum Error{
        ok,
        failed,
        alreadyExist
    }
}
