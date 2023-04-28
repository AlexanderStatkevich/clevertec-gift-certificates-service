package ru.clevertec.statkevich.giftcertificatesservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.statkevich.giftcertificatesservice.dao.ITagDao;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.mapper.TagMapper;

import java.util.List;


/**
 * Described class bind storage and output of application.
 * As well as uses CRUD operations for this purpose.
 */
@RequiredArgsConstructor
@Service
public class TagService implements ITagService {

    private final ITagDao tagDao;

    private final TagMapper tagMapper;

    @Override
    public Long create(Tag tag) {
        return tagDao.create(tag);
    }

    @Override
    public Tag findById(Long id) {
        return tagDao.findById(id);
    }

    @Override
    public List<Tag> findAll() {
        return tagDao.findAll();
    }

    @Override
    public void update(Long id, TagCreateUpdateDto tagCreateUpdateDto) {
        Tag tag = tagDao.findById(id);
        tagMapper.map(tagCreateUpdateDto, tag);
        tagDao.update(tag);
    }

    @Override
    public void delete(Long id) {
        tagDao.delete(id);
    }
}
