package pl.chojecki.rent_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.chojecki.rent_service.model.Order;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o")
    List<Order> findAllOrders(Pageable page);

    @Query("select o from Order o where o.clientID=:#{#clientId}")
    List<Order> findAllByClientID(Long clientId);
}
