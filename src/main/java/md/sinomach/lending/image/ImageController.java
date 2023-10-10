package md.sinomach.lending.image;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.image.dto.AddImageResponse;
import md.sinomach.lending.image.dto.GetImageResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public AddImageResponse addImage(@RequestParam("file") MultipartFile file) {
        return imageService.addImage(file);
    }

    @GetMapping("/getData/{id}")
    public GetImageResponse getImage(@PathVariable("id") Long id) {
        return imageService.getImageData(id);
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImageById(@PathVariable("id") Long id) {
        return imageService.getImageById(id);
    }

   @PostMapping("/downloadAllProductImages")
   public void downloadAllProductImages(){
        imageService.downloadAllProductImages();
    }
}
