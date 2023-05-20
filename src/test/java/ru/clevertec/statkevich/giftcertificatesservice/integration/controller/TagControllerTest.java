package ru.clevertec.statkevich.giftcertificatesservice.integration.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagCreateUpdateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.TagVo;
import ru.clevertec.statkevich.giftcertificatesservice.integration.BaseIntegrationTest;
import ru.clevertec.statkevich.giftcertificatesservice.utils.TestTagControllerData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void updateIntegrationTest() throws Exception {

        TagCreateUpdateDto tagCreateUpdateDto = new TagCreateUpdateDto("test");

        mockMvc.perform(patch("/tags/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tagCreateUpdateDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteIntegrationTest() throws Exception {

        mockMvc.perform(delete("/tags/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findMostUsedTagIntegrationTest() throws Exception {
        TagVo response = new TagVo(3L, "test3");

        mockMvc.perform(get("/tags/most-used")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Nested
    @DisplayName("Tests on create controller method")
    class TestsOnCreate {
        @Test
        void createIntegrationTest() throws Exception {
            TagCreateUpdateDto tagCreateDto = new TagCreateUpdateDto("test tag");

            mockMvc.perform(post("/tags")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(tagCreateDto)))
                    .andExpect(status().isCreated())
                    .andExpect(content().string("4"));
        }

        @Test
        void wrongInputCreateIntegrationTest() throws Exception {
            TagCreateUpdateDto tagCreateDto = new TagCreateUpdateDto("");

            mockMvc.perform(post("/tags")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(tagCreateDto)))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Tests on find controller methods")
    class TestsOnFind {
        @Test
        void findByIdIntegrationTest() throws Exception {
            TagVo response = TestTagControllerData.buildApiFindByIdResponse();

            mockMvc.perform(get("/tags/{id}", 1)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(response)));
        }

        @Test
        void findByIdWrongInputIntegrationTest() throws Exception {
            mockMvc.perform(get("/tags/{id}", 6)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
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
}
