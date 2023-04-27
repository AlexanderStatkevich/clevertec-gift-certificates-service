package ru.clevertec.statkevich.giftcertificatesservice.dto;

import jakarta.validation.constraints.NotBlank;

public record TagDto(

        @NotBlank
        Long id,

        @NotBlank
        String name
) {
}
