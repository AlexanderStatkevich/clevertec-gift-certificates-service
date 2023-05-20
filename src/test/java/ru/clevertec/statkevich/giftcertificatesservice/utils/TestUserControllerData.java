package ru.clevertec.statkevich.giftcertificatesservice.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import ru.clevertec.statkevich.giftcertificatesservice.dto.UserVo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class TestUserControllerData {

    private static final ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder().build();

    public static UserVo buildApiFindByIdResponse() throws IOException {
        String json = load("__files/user_controller/api_find_by_id_response.json");
        return objectMapper.readValue(json, UserVo.class);
    }

    public static Page<UserVo> buildApiFindAllResponse() throws IOException {
        String json = load("__files/user_controller/api_find_all_response.json");
        List<UserVo> userVoList = objectMapper.readValue(json, new TypeReference<>() {
        });
        return new PageImpl<>(userVoList, Pageable.ofSize(20), 1);
    }

    private static String load(String fileName) throws IOException {
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(fileName);
        return IOUtils.toString(Objects.requireNonNull(systemResourceAsStream), "UTF-8");
    }
}
