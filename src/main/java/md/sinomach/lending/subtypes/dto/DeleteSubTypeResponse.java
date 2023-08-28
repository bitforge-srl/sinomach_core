package md.sinomach.lending.subtypes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteSubTypeResponse {

    private boolean success;
    private DeleteSubTypeResponse.Error error;

    public static DeleteSubTypeResponse success() {
        return DeleteSubTypeResponse.builder()
                .success(true)
                .error(DeleteSubTypeResponse.Error.ok)
                .build();
    }

    public static DeleteSubTypeResponse failed(DeleteSubTypeResponse.Error error) {
        return DeleteSubTypeResponse.builder()
                .success(false)
                .error(error)
                .build();
    }

    public enum Error {
        ok,
        failed,
        non_existent_subtype
    }

}
