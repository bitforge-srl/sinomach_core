package md.sinomach.lending.image;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.image.dto.AddImageResponse;
import md.sinomach.lending.image.dto.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public AddImageResponse addImage(Long id, MultipartFile file, String nameFile) {

        Image savedImage;
        try {
            byte[] bytes = file.getBytes();
            Image image = new Image(id, nameFile, bytes);
            savedImage = imageRepository.save(image);
        } catch (Exception e) {
            return AddImageResponse.failed(AddImageResponse.Error.failed);
        }
        return AddImageResponse.success(savedImage.getId());
    }
}
