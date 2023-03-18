package md.sinomach.lending.feedback;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

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
