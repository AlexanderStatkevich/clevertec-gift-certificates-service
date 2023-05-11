package ru.clevertec.statkevich.giftcertificatesservice.service;

import ru.clevertec.statkevich.giftcertificatesservice.domain.User;

public interface IUserService {
    User findById(Long id);
}
