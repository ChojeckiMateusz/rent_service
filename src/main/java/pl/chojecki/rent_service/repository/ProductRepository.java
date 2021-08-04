package pl.chojecki.rent_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.chojecki.rent_service.model.Product;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p")
    List<Product> findAllProducts(Pageable page);

    @Query("select  p from  Product p where p.name=:#{#productName}")
    Optional<Product> findByName(String productName);
}
