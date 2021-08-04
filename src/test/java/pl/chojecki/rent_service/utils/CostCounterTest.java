package pl.chojecki.rent_service.utils;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.chojecki.rent_service.model.Order;
import pl.chojecki.rent_service.model.Price;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CostCounterTest {

    @Autowired
    private CostCounter costCounter = new CostCounter();

    @Test
    void testCountTotalCost() {
        //given
        Order order = new Order();
        order.setId(1L);
        order.setClientID(1L);
        order.setProductID(1L);
        order.setStartDate(LocalDateTime.of(2021, 8, 1, 12, 30, 0));
        order.setEndDate(LocalDateTime.of(2021, 8, 1, 12, 40, 0));

        Price price = new Price();
        price.setId(1L);
        price.setPrice(BigDecimal.valueOf(1.20));
        //when
        BigDecimal result = costCounter.countTotalCost(order, price);
        //then
        assertEquals(BigDecimal.valueOf(12.0), result);
    }
}