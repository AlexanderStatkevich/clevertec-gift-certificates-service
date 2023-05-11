package ru.clevertec.statkevich.giftcertificatesservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;
import ru.clevertec.statkevich.giftcertificatesservice.dto.OrderCreateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.OrderVo;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.OrderMapper;
import ru.clevertec.statkevich.giftcertificatesservice.service.IOrderService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class OrderController {

    private final IOrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/{userId}/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody OrderCreateDto orderCreateDto) {
        Order order = orderMapper.toEntity(orderCreateDto);
        return orderService.create(order);
    }

    @GetMapping("/{userId}/orders/{id}")
    public OrderVo findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return orderMapper.toDto(order);
    }

    @GetMapping("/{userId}/orders")
    public List<OrderVo> findAll(@PathVariable Long userId) {
        List<Order> orderList = orderService.findByUser(userId);
        return orderMapper.toDtoList(orderList);
    }
}
