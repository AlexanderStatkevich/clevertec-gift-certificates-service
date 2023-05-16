package ru.clevertec.statkevich.giftcertificatesservice.integration.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagVo;
import ru.clevertec.statkevich.giftcertificatesservice.integration.BaseIntegrationTest;
import ru.clevertec.statkevich.giftcertificatesservice.utils.TestTagControllerData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
public class TagControllerTest extends BaseIntegrationTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @Test
    void findByIdIntegrationTest() throws Exception {
        TagVo response = TestTagControllerData.buildApiFindByIdResponse();

        mockMvc.perform(get("/tags/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Test
    void findAllIntegrationTest() throws Exception {
        Page<TagVo> response = TestTagControllerData.buildApiFindAllResponse();

        mockMvc.perform(get("/tags")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }
}