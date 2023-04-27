package ru.clevertec.statkevich.giftcertificatesservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record GiftCertificateVo(

        Long id,

        String name,

        String description,

        BigDecimal price,

        Integer duration,

        LocalDateTime createDate,

        LocalDateTime lastUpdateDate,

        List<TagVo> tags
) {
}
