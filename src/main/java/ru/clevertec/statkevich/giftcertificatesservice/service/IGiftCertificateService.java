package ru.clevertec.statkevich.giftcertificatesservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;

public interface IGiftCertificateService {

    Long create(GiftCertificate giftCertificate);

    GiftCertificate findById(Long id);

    Page<GiftCertificate> findAll(Pageable pageable, GiftCertificateFilter filter);

    void update(Long id, GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto);

    void delete(Long id);
}
