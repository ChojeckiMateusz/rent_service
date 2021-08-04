package pl.chojecki.rent_service.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.chojecki.rent_service.model.Product;
import pl.chojecki.rent_service.repository.ProductRepository;
import pl.chojecki.rent_service.service.ProductService;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElseThrow();
    }
}
