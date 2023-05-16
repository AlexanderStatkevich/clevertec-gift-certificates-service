package ru.clevertec.statkevich.giftcertificatesservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.statkevich.giftcertificatesservice.domain.User;

public interface IUserService {
    User findById(Long id);

    Page<User> findAll(Pageable pageable);
}
