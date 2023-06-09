package ru.clevertec.statkevich.giftcertificatesservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.statkevich.giftcertificatesservice.dao.IGiftCertificateDao;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GiftCertificateServiceTest {

    @Mock
    private IGiftCertificateDao giftCertificateDao;

    @InjectMocks
    private GiftCertificateService giftCertificateService;

    @Test
    void checkCreateReturnCorrectId() {
        GiftCertificate certificate = GiftCertificate.builder().id(1L).name("test").build();
        when(giftCertificateDao.create(certificate)).thenReturn(1L);
        Long expected = giftCertificateService.create(certificate);
        assertThat(1L).isEqualTo(expected);
    }

    @Test
    void checkFindByIdMethodReturningCorrectEntity() {
        GiftCertificate expected = GiftCertificate.builder().id(1L).name("test").build();
        when(giftCertificateDao.findById(1L)).thenReturn(expected);
        GiftCertificate actual = giftCertificateService.findById(1L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindAllReturnCorrectList() {
        GiftCertificate certificate = GiftCertificate.builder().id(1L).name("test").build();
        List<GiftCertificate> expected = List.of(certificate);
        when(giftCertificateDao.findAll()).thenReturn(expected);
        List<GiftCertificate> actual = giftCertificateService.findAll(null);
        assertThat(actual).isEqualTo(expected);
    }
}
