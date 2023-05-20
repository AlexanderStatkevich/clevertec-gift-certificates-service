package ru.clevertec.statkevich.giftcertificatesservice.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;
import ru.clevertec.statkevich.giftcertificatesservice.dto.OrderCreateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.OrderVo;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.OrderMapper;
import ru.clevertec.statkevich.giftcertificatesservice.service.IOrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users")
public class OrderController {

    private final IOrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping("/{userId}/orders")
    public ResponseEntity<Long> create(@Valid @RequestBody OrderCreateDto orderCreateDto) {
        Order order = orderMapper.toEntity(orderCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(order));
    }

    @GetMapping("/{userId}/orders/{id}")
    public ResponseEntity<OrderVo> findById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(orderMapper.toDto(order));
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<Page<OrderVo>> findAll(@PathVariable Long userId, Pageable pageable) {
        Page<Order> orderPage = orderService.findByUser(userId, pageable);
        return ResponseEntity.ok(orderPage.map(orderMapper::toDto));
    }
}
