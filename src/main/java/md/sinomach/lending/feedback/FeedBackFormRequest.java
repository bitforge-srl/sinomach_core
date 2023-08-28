package md.sinomach.lending.feedback;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FeedBackFormRequest {
    private String email;
    private String name;
    private String phone;
    private String comment;
    private Long productId;
    private String productName;
}
