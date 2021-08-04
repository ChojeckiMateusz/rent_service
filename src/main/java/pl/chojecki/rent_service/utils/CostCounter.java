package pl.chojecki.rent_service.utils;

import org.springframework.stereotype.Component;
import pl.chojecki.rent_service.model.Order;
import pl.chojecki.rent_service.model.Price;

import java.math.BigDecimal;
import java.time.Duration;

@Component
public class CostCounter {

    public BigDecimal countTotalCost(Order order, Price price) {
        Duration duration = Duration.between(order.getStartDate(), order.getEndDate());
        BigDecimal minutes = new BigDecimal(duration.toMinutes());

        return minutes.multiply(price.getPrice());
    }
}
