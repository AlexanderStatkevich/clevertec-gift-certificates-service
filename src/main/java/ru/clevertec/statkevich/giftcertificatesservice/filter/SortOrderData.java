package ru.clevertec.statkevich.giftcertificatesservice.filter;

import jakarta.validation.constraints.NotBlank;

public record SortOrderData(
        @NotBlank
        String field,

        SortOrder sortOrder
) {
}
