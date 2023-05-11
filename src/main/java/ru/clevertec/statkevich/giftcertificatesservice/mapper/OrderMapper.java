package ru.clevertec.statkevich.giftcertificatesservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;
import ru.clevertec.statkevich.giftcertificatesservice.dto.OrderCreateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.OrderVo;

import java.util.List;

@Mapper(uses = BaseEntityMapper.class)
public interface OrderMapper {

    @Mapping(target = "customer", source = "customerId")
    @Mapping(target = "certificate", source = "certificateId")
    Order toEntity(OrderCreateDto source);

    OrderVo toDto(Order source);

    List<OrderVo> toDtoList(List<Order> source);
}
