package ru.clevertec.statkevich.giftcertificatesservice.integration;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.clevertec.statkevich.giftcertificatesservice.domain.User;
import ru.clevertec.statkevich.giftcertificatesservice.service.UserService;
import ru.clevertec.statkevich.giftcertificatesservice.utils.TestData;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceTest extends BaseIntegrationTest {

    private final UserService userService;

    @Test
    void checkFindByIdReturnCorrectUser() throws IOException {
        User actual = userService.findById(1L);
        User expected = TestData.buildUser();
        assertThat(actual).isEqualTo(expected);
    }
}
