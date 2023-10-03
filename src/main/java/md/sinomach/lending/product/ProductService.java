package md.sinomach.lending.product;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.image.dto.AddImageRequest;
import md.sinomach.lending.image.dto.AddImageResponse;
import md.sinomach.lending.product.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Set<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return new HashSet<>(allProducts);
    }

    public Optional<Product> getProductByID(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> getSimilarProductsByProduct(Product product) {
        return product.getSubType().getProducts();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public AddProductResponse addProduct(Product product) {

        if (productRepository.existsByName(product.getName())) {
            return AddProductResponse.failed(AddProductResponse.Error.alreadyExist);
        }
        try {
            productRepository.save(product);
        } catch (Exception e) {
            return AddProductResponse.failed(AddProductResponse.Error.failed);
        }
        return AddProductResponse.success();
    }

    public DeleteProductResponse removeProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return DeleteProductResponse.failed(DeleteProductResponse.Error.non_existent_product);
        }
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            return DeleteProductResponse.failed(DeleteProductResponse.Error.failed);
        }
        return DeleteProductResponse.success();
    }

    public EditProductResponse editProduct(EditProductRequest editProductRequest) {
        try {
            Product product = productRepository.getReferenceById(editProductRequest.getId());
            product.setName(editProductRequest.getName());
            product.setFullDescription(editProductRequest.getFullDescription());
            product.setAdditionalDescription(editProductRequest.getAdditionalDescription());
            product.setShortSpecification(editProductRequest.getShortSpecification());
            product.setContent(editProductRequest.getContent());
            product.setImg(editProductRequest.getImg());
            product.setSubType(editProductRequest.getSubType());
            productRepository.save(product);
        } catch (Exception e) {
            return EditProductResponse.failed(EditProductResponse.Error.failed);
        }

        return EditProductResponse.success();
    }

}
