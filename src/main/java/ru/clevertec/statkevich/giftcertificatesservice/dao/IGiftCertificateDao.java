package ru.clevertec.statkevich.giftcertificatesservice.dao;

import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;

import java.util.List;

public interface IGiftCertificateDao extends IDao<GiftCertificate> {

    List<GiftCertificate> findAll(GiftCertificateFilter filter);
}
