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
import ru.clevertec.statkevich.giftcertificatesservice.dto.OrderCreateDto;
import ru.clevertec.statkevich.giftcertificatesservice.dto.OrderVo;
import ru.clevertec.statkevich.giftcertificatesservice.integration.BaseIntegrationTest;
import ru.clevertec.statkevich.giftcertificatesservice.utils.TestOrderControllerData;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@ActiveProfiles(profiles = "test")
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest extends BaseIntegrationTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;


    @Nested
    @DisplayName("Tests on create controller method")
    class TestsOnCreate {
        @Test
        void createIntegrationTest() throws Exception {
            OrderCreateDto orderCreateDto = new OrderCreateDto(1L, 2L);

            mockMvc.perform(post("/users/{userId}/orders", 4)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(orderCreateDto)))
                    .andExpect(status().isCreated())
                    .andExpect(content().string("5"));
        }

        @Test
        void wrongInputCreateIntegrationTest() throws Exception {
            OrderCreateDto orderCreateDto = new OrderCreateDto(0L, 2L);

            mockMvc.perform(post("/users/{userId}/orders", 4)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(orderCreateDto)))
                    .andExpect(status().is5xxServerError());
        }
    }


    @Nested
    @DisplayName("Tests on find controller methods")
    class TestsOnFind {
        @Test
        void findByIdIntegrationTest() throws Exception {
            OrderVo response = TestOrderControllerData.buildApiFindByIdResponse();

            mockMvc.perform(get("/users/{userId}/orders/{id}", 1, 1)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(response)));
        }

        @Test
        void findByIdWrongInputIntegrationTest() throws Exception {

            mockMvc.perform(get("/users/{userId}/orders/{id}", 1, 5)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        void findAllIntegrationTest() throws Exception {
            Page<OrderVo> response = TestOrderControllerData.buildApiFindAllResponse();

            mockMvc.perform(get("/users/{userId}/orders", 3)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(objectMapper.writeValueAsString(response)));
        }
    }
}
