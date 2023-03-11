package md.sinomach.lending.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
@Data
@Builder
public class FeedBackFormRequest {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private String comment;
    private Long productId;
    private Date time;
}
