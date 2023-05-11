package ru.clevertec.statkevich.giftcertificatesservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.statkevich.giftcertificatesservice.dao.ITagDao;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private ITagDao tagDao;

    @InjectMocks
    private TagService tagService;

    @Test
    void checkCreateReturnCorrectId() {
        Tag tag = Tag.builder().id(1L).name("test").build();
        when(tagDao.create(tag)).thenReturn(1L);
        Long expected = tagService.create(tag);
        assertThat(1L).isEqualTo(expected);
    }

    @Test
    void checkFindByIdMethodReturningCorrectEntity() {
        Tag expected = Tag.builder().id(1L).name("test").build();
        when(tagDao.findById(1L)).thenReturn(expected);
        Tag actual = tagService.findById(1L);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindAllReturnCorrectList() {
        Tag tag = Tag.builder().id(1L).name("test").build();
        List<Tag> expected = List.of(tag);
        when(tagDao.findAll()).thenReturn(expected);
        List<Tag> actual = tagService.findAll();
        assertThat(actual).isEqualTo(expected);
    }
}
