package pl.chojecki.rent_service.service.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.chojecki.rent_service.model.Order;
import pl.chojecki.rent_service.service.OrderService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void testAddOrder() {
        //given
        Order order = new Order();
        order.setClientID(1L);
        order.setProductID(1L);
        order.setStartDate(LocalDateTime.of(2021, 8, 1, 12, 30, 0));
        order.setEndDate(LocalDateTime.of(2021, 8, 1, 12, 40, 0));
        order.setTotalCost(BigDecimal.valueOf(0));
        //when
        Order newOrder = orderService.addOrder(order);
        //then
        assertThat(newOrder).isNotNull();
    }

    @Test
    void testGetAllCarsByClientIdAndByMonth() {
        //given
        //when
        List<Order> orders = orderService.getAllCarsByClientIdAndByMonth(1L, 8);
        //then
        assertThat(orders).isNotNull();
    }
}