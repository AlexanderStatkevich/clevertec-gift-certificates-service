package ru.clevertec.statkevich.giftcertificatesservice.filter;

import java.util.List;

/**
 * Class represent filtering parameters for
 * findAll query in {@link ru.clevertec.statkevich.giftcertificatesservice.controller.GiftCertificatesController}
 */
public record GiftCertificateFilter(

        /**
         * Using for search by specific tag
         */
        String tag,
        /**
         * Using for search by name or part of name
         */
        String name,
        /**
         * Using for search by description of part of description
         */
        String description,
        /**
         * Using for order data by parameters in {@link SortOrderData}
         */
        List<SortOrderData> sortOrderData

) {
}
