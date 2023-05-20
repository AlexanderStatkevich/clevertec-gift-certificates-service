package ru.clevertec.statkevich.giftcertificatesservice.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.statkevich.giftcertificatesservice.domain.User;
import ru.clevertec.statkevich.giftcertificatesservice.repository.UserRepository;
import ru.clevertec.statkevich.giftcertificatesservice.utils.TestData;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void checkFindByIdReturnCorrectUser() throws IOException {

        User expected = TestData.buildUser();
        when(userRepository.findById(1L)).thenReturn(Optional.of(expected));
        User actual = userService.findById(1L);
        assertThat(actual).isEqualTo(expected);
    }
}
