package md.sinomach.lending.image;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.image.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public AddImageResponse addImage(MultipartFile file) {

        Image savedImage;
        try {
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            byte[] bytes = file.getBytes();
            Image image = new Image(null, originalFilename, bytes, contentType);

            savedImage = imageRepository.save(image);
        } catch (Exception e) {
            return AddImageResponse.failed(AddImageResponse.Error.failed);
        }
        return AddImageResponse.success(savedImage.getId());
    }

    public GetImageResponse getImageData(Long id) {

        Image image = null;
        try {
            image = imageRepository.findById(id).get();

        } catch (Exception e) {
            return GetImageResponse.failed(GetImageResponse.Error.failed);
        }
        return GetImageResponse.success(image);
    }

    public byte[] getImageById(Long id) {

        Optional<Image> image = imageRepository.findById(id);
        byte[] bytes = image.orElseThrow(() -> new ImageNotFoundException(id)).getBytes();

        return GetImageByIdResponse.success(bytes);
    }
}
