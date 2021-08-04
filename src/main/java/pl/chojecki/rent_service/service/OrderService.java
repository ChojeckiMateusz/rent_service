package pl.chojecki.rent_service.service;

import pl.chojecki.rent_service.model.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);

    List<Order> getAllCarsByClientIdAndByMonth(Long clientId, int month);
}
