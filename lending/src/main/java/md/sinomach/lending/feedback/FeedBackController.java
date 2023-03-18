package md.sinomach.lending.feedback;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/feedback")
@RequiredArgsConstructor
public class FeedBackController {
    private final FeedBackService feedBackService;

    @PostMapping("insert")
    public FeedBackFormResponse processFeedBack(@RequestBody FeedBackFormRequest feedBackFormRequest) {
        return feedBackService.processingRequest(feedBackFormRequest);
    }
}
