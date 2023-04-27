package ru.clevertec.statkevich.giftcertificatesservice.filter;

import java.util.List;

public record GiftCertificateFilter(

        String tag,

        String name,

        String description,

        List<SortOrderData> sortOrderData

) {
}
