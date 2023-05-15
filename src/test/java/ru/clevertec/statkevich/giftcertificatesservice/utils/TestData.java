package ru.clevertec.statkevich.giftcertificatesservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import ru.clevertec.statkevich.giftcertificatesservice.domain.GiftCertificate;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Order;
import ru.clevertec.statkevich.giftcertificatesservice.domain.Tag;
import ru.clevertec.statkevich.giftcertificatesservice.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TestData {
    public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    static ObjectMapper objectMapper = new Jackson2ObjectMapperBuilder()
            .deserializerByType(LocalDate.class, new LocalDateDeserializer(DATE_FORMAT))
            .deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(DATE_TIME_FORMAT)).build();

    public static User buildUser() throws IOException {
        String json = load("__files/entities/test_user.json");
        return objectMapper.readValue(json, User.class);
    }

    public static Tag buildTag() throws IOException {
        String json = load("__files/entities/test_tag.json");
        return objectMapper.readValue(json, Tag.class);
    }

    public static GiftCertificate buildGiftCertificate() throws IOException {
        String json = load("__files/entities/test_certificate.json");
        return objectMapper.readValue(json, GiftCertificate.class);
    }

    public static Order buildOrder() throws IOException {
        String json = load("__files/entities/test_order.json");
        return objectMapper.readValue(json, Order.class);
    }

    private static String load(String fileName) throws IOException {
        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(fileName);
        return IOUtils.toString(Objects.requireNonNull(systemResourceAsStream));
    }
}
