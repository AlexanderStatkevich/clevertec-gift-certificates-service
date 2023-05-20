package ru.clevertec.statkevich.giftcertificatesservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagCreateUpdateDto;

public interface ITagService {

    Long create(Tag tag);

    Tag findById(Long id);

    Page<Tag> findAll(Pageable pageable);

    void update(Long id, TagCreateUpdateDto tagCreateUpdateDto);

    void delete(Long id);

    Tag findMostUsedTag();
}
