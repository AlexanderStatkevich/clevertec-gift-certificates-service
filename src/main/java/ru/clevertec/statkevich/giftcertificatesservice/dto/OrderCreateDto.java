package ru.clevertec.statkevich.giftcertificatesservice.dto;


public record OrderCreateDto(
        Long customerId,
        Long certificateId
) {
}
