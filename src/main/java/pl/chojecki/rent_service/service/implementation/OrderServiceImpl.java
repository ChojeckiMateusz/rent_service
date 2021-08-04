package pl.chojecki.rent_service.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.chojecki.rent_service.model.Order;
import pl.chojecki.rent_service.repository.OrderRepository;
import pl.chojecki.rent_service.repository.PriceRepository;
import pl.chojecki.rent_service.service.OrderService;
import pl.chojecki.rent_service.utils.CostCounter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CostCounter costCounter;

    private final PriceRepository priceRepository;

    @Override
    public Order addOrder(Order order) {
        System.out.println(order.getId());
        order.setTotalCost(costCounter.countTotalCost(order, priceRepository.getById(order.getProductID())));
        order = orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> getAllCarsByClientIdAndByMonth(Long clientId, int month) {
        List<Order> allOrders = orderRepository.findAllByClientID(clientId);

        List<Order> orders = new ArrayList<>();

        for (Order order : allOrders) {
            if(order.getStartDate().getMonthValue() == month)
                orders.add(order);
        }

        return orders;
    }
}
