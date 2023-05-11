package ru.clevertec.statkevich.giftcertificatesservice.service;

import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagCreateUpdateDto;

import java.util.List;

public interface ITagService {

    Long create(Tag tag);

    Tag findById(Long id);

    List<Tag> findAll();

    void update(Long id, TagCreateUpdateDto tagCreateUpdateDto);

    void delete(Long id);

    Tag findMostUsedTag();
}
