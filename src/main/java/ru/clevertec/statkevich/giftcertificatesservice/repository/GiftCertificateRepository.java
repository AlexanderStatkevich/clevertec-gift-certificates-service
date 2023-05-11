package ru.clevertec.statkevich.giftcertificatesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;

public interface GiftCertificateRepository extends JpaRepository<GiftCertificate, Long> {
}
