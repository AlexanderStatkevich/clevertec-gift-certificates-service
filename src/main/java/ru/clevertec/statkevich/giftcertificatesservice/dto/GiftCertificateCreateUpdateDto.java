package ru.clevertec.statkevich.giftcertificatesservice.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;

public record GiftCertificateCreateUpdateDto(

        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        BigDecimal price,
        @NotBlank
        Integer duration,

        List<TagDto> tags
) {
}
