package ru.clevertec.statkevich.giftcertificatesservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.statkevich.giftcertificatesservice.dao.IGiftCertificateDao;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.GiftCertificateMapper;

import java.util.List;


/**
 * Described class bind storage and output of application.
 * As well as uses CRUD operations for this purpose.
 */
@RequiredArgsConstructor
@Service
public class GiftCertificateService implements IGiftCertificateService {

    private final IGiftCertificateDao giftCertificateDao;

    private final GiftCertificateMapper giftCertificateMapper;

    @Override
    public Long create(GiftCertificate giftCertificate) {
        return giftCertificateDao.create(giftCertificate);
    }

    @Override
    public GiftCertificate findById(Long id) {
        return giftCertificateDao.findById(id);
    }

    @Override
    public List<GiftCertificate> findAll(GiftCertificateFilter filter) {
        return filter != null
                ? giftCertificateDao.findAll(filter)
                : giftCertificateDao.findAll();
    }

    @Override
    public void update(Long id, GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto) {
        GiftCertificate giftCertificate = giftCertificateDao.findById(id);
        giftCertificateMapper.map(giftCertificateCreateUpdateDto, giftCertificate);
        giftCertificateDao.update(giftCertificate);
    }

    @Override
    public void delete(Long id) {
        giftCertificateDao.delete(id);
    }
}
