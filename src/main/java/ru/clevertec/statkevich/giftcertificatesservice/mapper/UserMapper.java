package ru.clevertec.statkevich.giftcertificatesservice.mapper;


import org.mapstruct.Mapper;
import ru.clevertec.statkevich.giftcertificatesservice.domain.User;
import ru.clevertec.statkevich.giftcertificatesservice.dto.UserVo;

@Mapper
public interface UserMapper {

    UserVo toDto(User source);
}
