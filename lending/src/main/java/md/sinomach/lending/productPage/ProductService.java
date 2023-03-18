package md.sinomach.lending.productPage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Set<Product> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        return  new HashSet<>(allProducts);
    }

    public Optional<Product> getProductByID(Long id){
        return productRepository.findById(id);
    }

    public List<Product> getSimilarProductsByProduct(Product product){
       return product.getSubType().getProducts();
    }

    public Product save(Product product) {
       return  productRepository.save(product);
    }
}
