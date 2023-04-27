package ru.clevertec.statkevich.giftcertificatesservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.GiftCertificateVo;
import ru.clevertec.statkevich.giftcertificatesservice.filter.GiftCertificateFilter;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.GiftCertificateMapper;
import ru.clevertec.statkevich.giftcertificatesservice.service.GiftCertificateService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/certificates")
public class GiftCertificatesController {

    private final GiftCertificateService giftCertificateService;
    private final GiftCertificateMapper giftCertificateMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Validated @RequestBody GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto) {
        GiftCertificate giftCertificate = giftCertificateMapper.toEntity(giftCertificateCreateUpdateDto);
        return giftCertificateService.create(giftCertificate);
    }


    @GetMapping(path = "/{id}")
    public GiftCertificateVo getById(@PathVariable Long id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        return giftCertificateMapper.toDto(giftCertificate);
    }


    @GetMapping
    public List<GiftCertificateVo> getList(GiftCertificateFilter filter) {
        List<GiftCertificate> certificateList = giftCertificateService.findAll(filter);
        return giftCertificateMapper.toDtoList(certificateList);
    }


    @PatchMapping(path = "/{id}")
    public void update(@PathVariable Long id,
                       @Validated @RequestBody GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto) {
        giftCertificateService.update(id, giftCertificateCreateUpdateDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        giftCertificateService.delete(id);
    }
}
