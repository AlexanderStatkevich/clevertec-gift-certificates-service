package ru.clevertec.statkevich.giftcertificatesservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.statkevich.giftcertificatesservice.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
