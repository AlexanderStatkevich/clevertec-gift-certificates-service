package ru.clevertec.statkevich.giftcertificatesservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.TagMapper;
import ru.clevertec.statkevich.giftcertificatesservice.repository.TagRepository;

import java.util.List;


/**
 * Described class bind storage and output of application.
 * As well as uses CRUD operations for this purpose.
 */
@RequiredArgsConstructor
@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

    @Transactional
    @Override
    public Long create(Tag tag) {
        return tagRepository.save(tag).getId();
    }

    @Override
    public Tag findById(Long id) {
        return tagRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Transactional
    @Override
    public void update(Long id, TagCreateUpdateDto tagCreateUpdateDto) {
        Tag tag = findById(id);
        tagMapper.map(tagCreateUpdateDto, tag);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public Tag findMostUsedTag() {
        return tagRepository.findMostUsedTag();
    }
}
