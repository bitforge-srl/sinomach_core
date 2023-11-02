package md.sinomach.lending.image;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.image.dto.*;
import md.sinomach.lending.product.Product;
import md.sinomach.lending.product.ProductService;
import md.sinomach.lending.type.Type;
import md.sinomach.lending.type.TypeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ProductService productService;
    private final TypeService typeService;

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

    public DeleteImageResponse deleteImageByImgId(Long imgId) {
        try {
            imageRepository.deleteById(imgId);
        } catch (Exception e) {
            return DeleteImageResponse.failed(DeleteImageResponse.Error.failed);
        }
        return DeleteImageResponse.success(imgId);
    }

    public void downloadAllProductImages() {
        Set<Product> allProducts = productService.getAllProducts();

        for (Product product : allProducts) {
            String imgUrl = "https://cn-sinomach.md/" + product.getImg();
            File file = new File(imgUrl);
            String fileName = file.getName();

            Long imageId;
            try {
                byte[] bytes = recoverImageFromUrl(imgUrl);
                Image image = imageRepository.save(new Image(null, fileName, bytes, "image/jpeg"));
                imageId = image.getId();
                product.setImgId(imageId);
                productService.save(product);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void downloadAllTypesImages() {
        List<Type> allTypes = typeService.getAllTypes();

        for (Type type : allTypes) {
            String imgUrl = "https://cn-sinomach.md/" + type.getImg();
            File file = new File(imgUrl);
            String fileName = file.getName();

            Long imageId;
            try {
                byte[] bytes = recoverImageFromUrl(imgUrl);
                Image image = imageRepository.save(new Image(null, fileName, bytes, "image/png"));
                imageId = image.getId();
                type.setImgId(imageId);
                typeService.save(type);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }

    public byte[] recoverImageFromUrl(String imgUrl) throws Exception {
        URL url = new URL(imgUrl);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (InputStream inputStream = url.openStream()) {
            int n = 0;
            byte[] buffer = new byte[1024];
            while (-1 != (n = inputStream.read(buffer))) {
                output.write(buffer, 0, n);
            }
        }
        return output.toByteArray();
    }
}
