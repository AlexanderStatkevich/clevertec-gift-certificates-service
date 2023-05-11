package ru.clevertec.statkevich.giftcertificatesservice.service;

import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;

import java.util.List;

public interface IGiftCertificateService {

    Long create(GiftCertificate giftCertificate);

    GiftCertificate findById(Long id);

    List<GiftCertificate> findAll(GiftCertificateFilter filter);

    void update(Long id, GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto);

    void delete(Long id);
}
