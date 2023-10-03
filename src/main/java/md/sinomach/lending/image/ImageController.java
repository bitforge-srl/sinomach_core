package md.sinomach.lending.image;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.image.dto.AddImageRequest;
import md.sinomach.lending.image.dto.AddImageResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/upload")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/image")
    public AddImageResponse addImage(@RequestBody AddImageRequest addImageRequest) {

        return imageService.addImage(addImageRequest);
    }
}
