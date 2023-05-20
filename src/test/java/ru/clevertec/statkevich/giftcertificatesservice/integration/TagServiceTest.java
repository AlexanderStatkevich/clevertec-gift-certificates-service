package ru.clevertec.statkevich.giftcertificatesservice.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;
import ru.clevertec.statkevich.giftcertificatesservice.service.TagService;
import ru.clevertec.statkevich.giftcertificatesservice.utils.TestData;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TagServiceTest extends BaseIntegrationTest {

    private final TagService tagService;

    @Test
    void checkFindByIdReturnCorrectTag() throws IOException {
        Tag actual = tagService.findById(1L);
        Tag expected = TestData.buildTag();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void checkFindAllReturnCorrectListSize() {
        List<Tag> tagList = tagService.findAll();
        int expected = 3;
        assertThat(tagList.size()).isEqualTo(expected);
    }

    @Test
    void checkFidByIdReturnCorrectTag() {
        tagService.delete(1L);
    }
}
