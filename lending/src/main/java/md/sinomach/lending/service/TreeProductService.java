package md.sinomach.lending.service;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.Product;
import md.sinomach.lending.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreeProductService {
private final ProductRepository productRepository;
    public Set<Product> getAllProducts(){
        List<Product> allProducts = productRepository.findAll();
        return  new HashSet<>(allProducts);
    }
}
