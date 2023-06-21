package com.railway.base

import com.railway.RailwaySystemApplication
import com.railway.infrastructure.config.Profiles
import groovy.transform.TypeChecked
import org.junit.jupiter.api.BeforeAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.test.context.event.annotation.BeforeTestMethod
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity

@TypeChecked
@SpringBootTest(classes = [RailwaySystemApplication])
@ActiveProfiles("test")
@Transactional
@Rollback
abstract class IntegrationSpec extends Specification{
    @Autowired
    protected WebApplicationContext webApplicationContext

    MockMvc mockMvc

    @BeforeTestClass
    void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build()
    }
}
