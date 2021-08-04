package pl.chojecki.rent_service.service;

import pl.chojecki.rent_service.model.Product;

public interface ProductService {

    Product getProductById(Long id);

    Product getProductByName(String name);
}
