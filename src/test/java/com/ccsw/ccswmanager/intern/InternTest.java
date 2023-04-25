package com.ccsw.ccswmanager.intern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.ccswmanager.common.exception.AlreadyExistsException;
import com.ccsw.ccswmanager.config.mapper.BeanMapper;
import com.ccsw.ccswmanager.intern.model.InternDto;
import com.ccsw.ccswmanager.intern.model.InternEntity;

@ExtendWith(MockitoExtension.class)
public class InternTest {

    @Mock
    private InternRepository internRepository;

    @InjectMocks
    private InternServiceImpl internService;

    @Mock
    private BeanMapper beanMapper;

    private static String USERNAME = "testusername";
    private static String NAME = "testname";
    private static String EMAIL = "testemail@test.com";
    private static Long QUANTITY = 2L;

    @Test
    void saveShouldSave() throws AlreadyExistsException {
        InternEntity intern = new InternEntity();
        intern.setUsername(USERNAME);
        intern.setEmail(EMAIL);

        when(internRepository.findByUsername(intern.getUsername())).thenReturn(null);
        when(internRepository.findByEmail(intern.getEmail())).thenReturn(null);
        when(internRepository.save(intern)).thenReturn(intern);

        InternEntity result = internService.save(intern);

        assertEquals(intern, result);
    }

    @Test
    void saveExistsByUsernameShouldThrowAlreadyExistsException() {
        InternEntity intern = new InternEntity();
        intern.setUsername(USERNAME);
        intern.setEmail(EMAIL);

        when(internRepository.findByUsername(intern.getUsername())).thenReturn(intern);

        assertThrows(AlreadyExistsException.class, () -> {
            internService.save(intern);
        });
    }

    @Test
    void saveExistsByEmailShouldThrowAlreadyExistsException() {
        InternEntity intern = new InternEntity();
        intern.setUsername(USERNAME);
        intern.setEmail(EMAIL);

        when(internRepository.findByUsername(intern.getUsername())).thenReturn(null);
        when(internRepository.findByEmail(intern.getEmail())).thenReturn(intern);

        assertThrows(AlreadyExistsException.class, () -> {
            internService.save(intern);
        });
    }

    @Test
    void savePredictShouldSaveQuantityTimes() {

        // given
        InternDto dto = new InternDto();
        dto.setUsername("testusername");
        dto.setEmail("testemail@test.com");

        InternEntity entity = new InternEntity();
        entity.setUsername("testusername");
        entity.setEmail("testemail@test.com");

        when(beanMapper.map(dto, InternEntity.class)).thenReturn(entity);

        // when
        internService.savePredict(dto, QUANTITY);

        // then
        verify(internRepository, times(2)).save(entity);

    }

}
