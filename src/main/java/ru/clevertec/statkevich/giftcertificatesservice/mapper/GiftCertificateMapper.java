package ru.clevertec.statkevich.giftcertificatesservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateVo;

import java.util.List;

@Mapper
public interface GiftCertificateMapper {

    GiftCertificate toEntity(GiftCertificateCreateUpdateDto source);

    GiftCertificateVo toDto(GiftCertificate source);

    void map(GiftCertificateCreateUpdateDto source, @MappingTarget GiftCertificate target);


    List<GiftCertificateVo> toDtoList(List<GiftCertificate> source);
}
