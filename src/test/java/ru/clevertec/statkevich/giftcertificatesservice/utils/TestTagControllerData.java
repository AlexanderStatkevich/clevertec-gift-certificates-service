package ru.clevertec.statkevich.giftcertificatesservice.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagVo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class TestTagControllerData {

    static ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();

    public static TagVo buildApiFindByIdResponse() throws IOException {
        String json = load("__files/tag_controller/api_find_by_id_response.json");
        return objectMapper.readValue(json, TagVo.class);
    }

    public static Page<TagVo> buildApiFindAllResponse() throws IOException {
        String json = load("__files/tag_controller/api_find_all_response.json");
        List<TagVo> tagVoList = objectMapper.readValue(json, new TypeReference<>() {
        });
        return new PageImpl<>(tagVoList, Pageable.ofSize(20), 1);
    }

    private static String load(String fileName) throws IOException {
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(fileName);
        return IOUtils.toString(Objects.requireNonNull(systemResourceAsStream), "UTF-8");
    }

}
