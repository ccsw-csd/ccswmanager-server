package com.ccsw.ccswmanager.intern;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.intern.model.InternEntity;

@ExtendWith(MockitoExtension.class)
public class InternTest {

    @Mock
    private InternRepository internRepository;

    @InjectMocks
    private InternServiceImpl internService;

    private static String USERNAME = "testusername";

    private static String EMAIL = "testemail@test.com";

    @Test
    public void saveExistsUsernameShouldThrowException() {

        InternEntity intern = mock(InternEntity.class);
        intern.setUsername(USERNAME);
        intern.setEmail(EMAIL);

        when(internRepository.existsByUsername(intern.getUsername())).thenReturn(true);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            internService.save(intern);
        });
    }

    @Test
    public void saveExistsEmailShouldThrowException() {

        InternEntity intern = mock(InternEntity.class);
        intern.setUsername(USERNAME);
        intern.setEmail(EMAIL);

        when(internRepository.existsByUsername(intern.getUsername())).thenReturn(false);
        when(internRepository.existsByEmail(intern.getEmail())).thenReturn(true);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            internService.save(intern);
        });
    }

    @Test
    public void saveShouldSaveIntern() throws AlreadyExistsException {
        InternEntity intern = new InternEntity();
        intern.setUsername(USERNAME);
        intern.setEmail(EMAIL);

        when(internRepository.existsByUsername(intern.getUsername())).thenReturn(false);
        when(internRepository.existsByEmail(intern.getEmail())).thenReturn(false);
        when(internRepository.save(intern)).thenReturn(intern);

        InternEntity savedIntern = internService.save(intern);

        Assertions.assertNotNull(savedIntern);
        Assertions.assertEquals(intern.getUsername(), savedIntern.getUsername());
        Assertions.assertEquals(intern.getEmail(), savedIntern.getEmail());
    }

}
