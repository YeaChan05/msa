package org.yechan.orderservice.service;

import org.springframework.stereotype.Service;
import org.yechan.orderservice.dto.OrderDto;
import org.yechan.orderservice.jpa.OrderEntity;

@Service
public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
