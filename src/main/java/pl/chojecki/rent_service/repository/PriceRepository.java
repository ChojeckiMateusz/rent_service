package pl.chojecki.rent_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.chojecki.rent_service.model.Price;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p")
    List<Price> findAllPrices(Pageable page);

    @Query("select p from Price p where p.id=:#{#id}")
    Optional<Price> findById(Long id);
}
