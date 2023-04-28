package ru.clevertec.statkevich.giftcertificatesservice.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@Testcontainers
class GiftCertificateDaoTest {
    @Container
    private static final PostgreSQLContainer<?> postgreSqlContainer = new PostgreSQLContainer<>("postgres:15.1-alpine")
            .withDatabaseName("certificatesDB")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("test.sql");

    @Autowired
    private GiftCertificateDao giftCertificateDao;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSqlContainer::getUsername);
        registry.add("spring.datasource.password", postgreSqlContainer::getPassword);
    }

    @Test
    void checkCreateMethodAddCorrectEntity() {
        GiftCertificate expected = GiftCertificate.builder().id(1L).name("test").build();
        giftCertificateDao.create(expected);
        GiftCertificate actual = giftCertificateDao.findById(1L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindAllReturnCorrectListOfEntities() {
        GiftCertificate giftCertificate = GiftCertificate.builder().name("test").build();
        List<GiftCertificate> expected = List.of(giftCertificate);
        giftCertificateDao.create(giftCertificate);
        List<GiftCertificate> actual = giftCertificateDao.findAll(new GiftCertificateFilter(null, null, null, null));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkUpdateWorksProperly() {
        GiftCertificate giftCertificate = GiftCertificate.builder().id(1L).name("test").build();
        giftCertificateDao.create(giftCertificate);
        GiftCertificate toUpdate = GiftCertificate.builder().id(1L).name("updated").build();
        giftCertificateDao.update(toUpdate);
        GiftCertificate actual = giftCertificateDao.findById(1L);
        assertThat(actual).isEqualTo(toUpdate);
    }

}
