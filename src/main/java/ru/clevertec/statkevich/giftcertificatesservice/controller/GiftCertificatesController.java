package ru.clevertec.statkevich.giftcertificatesservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

/**
 * Described class expose REST API to perform
 * CRUD operations for Gift Certificates.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/certificates")
public class GiftCertificatesController {

    private final GiftCertificateService giftCertificateService;
    private final GiftCertificateMapper giftCertificateMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Long> create(@Valid @RequestBody GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto) {
        GiftCertificate giftCertificate = giftCertificateMapper.toEntity(giftCertificateCreateUpdateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(giftCertificateService.create(giftCertificate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftCertificateVo> findById(@PathVariable Long id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        return ResponseEntity.ok(giftCertificateMapper.toDto(giftCertificate));
    }

    /**
     * @param filter represent filtering and ordering passed in path variables
     * @return list of Gift Certificates
     */
    @GetMapping
    public ResponseEntity<Page<GiftCertificateVo>> findAll(Pageable pageable, GiftCertificateFilter filter) {
        Page<GiftCertificate> certificatePage = giftCertificateService.findAll(pageable, filter);
        return ResponseEntity.ok(certificatePage.map(giftCertificateMapper::toDto));
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id,
                       @Valid @RequestBody GiftCertificateCreateUpdateDto giftCertificateCreateUpdateDto) {
        giftCertificateService.update(id, giftCertificateCreateUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        giftCertificateService.delete(id);
    }
}
