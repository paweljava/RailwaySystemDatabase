package com.railway.base

import com.railway.RailwaySystemApplication
import groovy.transform.TypeChecked
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity

@TypeChecked
@SpringBootTest(classes = [RailwaySystemApplication])
//@ActiveProfiles([Profiles.TEST])
@Transactional
@Rollback
abstract class IntegrationSpec extends Specification {

    @Autowired
    protected WebApplicationContext webApplicationContext

    MockMvc mockMvc

    @BeforeEach
    void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build()
    }
}
