package com.ccsw.ccswmanager;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.ccsw.ccswmanager.utils.UtilsService;

@TestPropertySource(locations = { "classpath:application.properties", "classpath:application-test.properties" })
@SpringBootTest
public class PruebaTest {

    @Autowired
    UtilsService utilsService;

    @Test
    public void Prueba() {

        System.out.println(utilsService);
        assertThat("?").isEqualTo(utilsService.getVersion());
    }
}
