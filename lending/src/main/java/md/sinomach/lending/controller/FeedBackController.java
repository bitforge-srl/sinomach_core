package md.sinomach.lending.controller;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dto.FeedBackFormRequest;
import md.sinomach.lending.dto.FeedBackFormResponse;
import md.sinomach.lending.service.FeedBackService;
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
    public FeedBackFormResponse sendFeedBackMessage(@RequestBody FeedBackFormRequest feedBackFormRequest) {


        feedBackService.processingRequest(feedBackFormRequest);

        return FeedBackFormResponse.success();

    }
}
