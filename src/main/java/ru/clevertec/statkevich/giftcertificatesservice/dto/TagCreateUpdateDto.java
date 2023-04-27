package ru.clevertec.statkevich.giftcertificatesservice.dto;

import jakarta.validation.constraints.NotBlank;

public record TagCreateUpdateDto(
        @NotBlank
        String name
) {
}
