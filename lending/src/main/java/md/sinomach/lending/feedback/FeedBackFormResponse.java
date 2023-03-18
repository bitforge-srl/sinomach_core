package md.sinomach.lending.feedback;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedBackFormResponse {
    private boolean success;
    private String error;

    public static FeedBackFormResponse success(){
        return FeedBackFormResponse.builder()
                .success(true)
                .error("ok")
                .build();
    }

    public  static FeedBackFormResponse failed(String errorMessage){
        return FeedBackFormResponse.builder()
                .success(false)
                .error(errorMessage)
                .build();
    }
}
