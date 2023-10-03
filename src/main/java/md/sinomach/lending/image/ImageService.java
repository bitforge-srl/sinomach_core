package md.sinomach.lending.image;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.image.dto.AddImageRequest;
import md.sinomach.lending.image.dto.AddImageResponse;
import md.sinomach.lending.image.dto.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public AddImageResponse addImage(AddImageRequest addImageRequest) {

        try {
            byte[] bytes = addImageRequest.getBytes();
            String nameFile = addImageRequest.getNameFile();
            Image image = new Image(null, nameFile, bytes);
            imageRepository.save(image);
        } catch (Exception e) {
            return AddImageResponse.failed(AddImageResponse.Error.failed);
        }
        return AddImageResponse.success();
    }
}
