package ru.clevertec.statkevich.giftcertificatesservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.GiftCertificateMapper;
import ru.clevertec.statkevich.giftcertificatesservice.repository.GiftCertificateCriteriaRepository;
import ru.clevertec.statkevich.giftcertificatesservice.repository.GiftCertificateRepository;


/**
 * Described class bind storage and output of application.
 * As well as uses CRUD operations for this purpose.
 */
@RequiredArgsConstructor
@Service
public class GiftCertificateService implements IGiftCertificateService {

    private final GiftCertificateRepository giftCertificateRepository;

    private final GiftCertificateCriteriaRepository giftCertificateCriteriaRepository;
    private final GiftCertificateMapper giftCertificateMapper;

    @Override
    public Long create(GiftCertificate giftCertificate) {
        return giftCertificateRepository.save(giftCertificate).getId();
    }

    @Override
    public GiftCertificate findById(Long id) {
        return giftCertificateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    @Override
    public Page<GiftCertificate> findAll(Pageable pageable, GiftCertificateFilter filter) {
        return filter != null
                ? giftCertificateCriteriaRepository.findAllFiltered(pageable, filter)
                : giftCertificateRepository.findAll(pageable);
    }



    @Transactional
    @Override
    public void update(Long id, GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto) {
        GiftCertificate giftCertificate = giftCertificateRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        giftCertificateMapper.map(giftCertificateCreateUpdateDto, giftCertificate);
    }

    @Override
    public void delete(Long id) {
        giftCertificateRepository.deleteById(id);
    }
}
