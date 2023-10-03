package md.sinomach.lending.image;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.image.dto.AddImageResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/upload")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/image")
    public AddImageResponse addImage(@RequestParam("id") Long id,
                                     @RequestParam("file") MultipartFile file,
                                     @RequestParam("nameFile") String nameFile) {

        return imageService.addImage(id, file, nameFile);
    }
}
