package pl.chojecki.rent_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.chojecki.rent_service.model.Order;
import pl.chojecki.rent_service.repository.OrderRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@Transactional
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderRepository orderRepository;


    @Test
    void testNewOrder() throws Exception {
        //given
        //when
        MvcResult mvcResult = mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"clientID\": 1, \"productName\": \"PRALKA\", \"startDate\": \"2021-08-01 12:30:00\", \"endDate\": \"2021-08-01 12:40:00\"}")
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
        //then
        assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
    }

    @Test
    void testOrders() throws Exception {
        //given
        Order order = new Order();
        order.setClientID(1L);
        order.setProductID(1L);
        order.setStartDate(LocalDateTime.of(2021, 8, 1, 12, 30, 0));
        order.setEndDate(LocalDateTime.of(2021, 8, 1, 12, 40, 0));
        order.setTotalCost(BigDecimal.valueOf(0));
        orderRepository.save(order);
        //when
        MvcResult mvcResult = mockMvc.perform(get("/order/1/8"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();
        //then
//        List<Order> orderList = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Order>>() {});
        assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
    }
}