package org.scoula.security.util;

import lombok.extern.log4j.Log4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class, SecurityConfig.class })
@Log4j
class JwtProcessorTest {
    @Autowired
    JwtProcessor jwtProcessor;

    @Test
    void generateToken() {
        String username = "user0";
        String token = jwtProcessor.generateToken(username);
        log.info(token);
        assertNotNull(token); // null인지 확인
    }

    @Test
    void getUsername() {
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMCIsImlhdCI6MTcyNDI0MDA3NywiZXhwIjoxNzI0MjQwMzc3fQ.6ucWfP3JXMai_FiCR8rURz9VSiNVV4glu3GAEUS6nfsOhazjPiLBV4u2W6KgEnipHeDLpXKBi80Zow5afrozdw";
        String username = jwtProcessor.getUsername(token);
        log.info(username);
        assertNotNull(username);
    }

    @Test
    void validateToken() {
    }
}