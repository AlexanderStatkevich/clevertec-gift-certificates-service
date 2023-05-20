package ru.clevertec.statkevich.giftcertificatesservice.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.service.GiftCertificateService;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GiftCertificateServiceTest extends BaseIntegrationTest {

    private final GiftCertificateService giftCertificateService;

    @Test
    void integrationTest() {
        int expectedSizeBefore = 3;
        assertThat(giftCertificateService.findAll(null).size()).isEqualTo(expectedSizeBefore);
        giftCertificateService.create(GiftCertificate.builder().build());
        int expectedSizeAfterCreateCertificate = 4;
        assertThat(giftCertificateService.findAll(null).size()).isEqualTo(expectedSizeAfterCreateCertificate);
        giftCertificateService.delete(4L);
        assertThat(giftCertificateService.findAll(null).size()).isEqualTo(expectedSizeBefore);
    }
}
