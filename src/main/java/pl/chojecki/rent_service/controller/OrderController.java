package pl.chojecki.rent_service.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.chojecki.rent_service.dto.OrderDTO;
import pl.chojecki.rent_service.model.Order;
import pl.chojecki.rent_service.service.OrderService;
import pl.chojecki.rent_service.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<OrderDTO> newOrder(@RequestBody OrderDTO newOrder) {
        return new ResponseEntity<>(convertToDTO(orderService.addOrder(convertToEntity(newOrder))), HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}/{month}")
    public ResponseEntity<List<OrderDTO>> orders(@PathVariable Long clientId, @PathVariable int month) {
        List<OrderDTO> orderDTOs = new ArrayList<>();

        for (Order order: orderService.getAllCarsByClientIdAndByMonth(clientId, month))
            orderDTOs.add(convertToDTO(order));

        return new ResponseEntity<>(orderDTOs, HttpStatus.OK);
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setClientID(orderDTO.getClientID());
        order.setProductID(productService.getProductByName(orderDTO.getProductName()).getId());
        order.setStartDate(orderDTO.getStartDate());
        order.setEndDate(orderDTO.getEndDate());

        return order;
    }

    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setClientID(order.getClientID());
        orderDTO.setProductName(productService.getProductById(order.getProductID()).getName());
        orderDTO.setStartDate(order.getStartDate());
        orderDTO.setEndDate(order.getEndDate());
        orderDTO.setTotalCost(order.getTotalCost());

        return orderDTO;
    }
}
