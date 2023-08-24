package com.railway.train.infrastructure

import com.railway.base.IntegrationSpec
import com.railway.train.domain.SampleTrain
import com.railway.train.domain.TrainConfiguration
import com.railway.train.domain.TrainFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.MockMvcConfigurer
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class TrainFacadeTestIntegration extends IntegrationSpec implements SampleTrain {

    TrainFacade trainFacade = new TrainConfiguration().trainFacade()

    @WithMockUser
    def "should get trains"() {
        given: 'inventory has "Inka", "Awangarda", "Stańczyk"'
        /*mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build()*/
        trainFacade.addTrain(inka)
        trainFacade.addTrain(awangarda)
        trainFacade.addTrain(stanczyk)

        when: 'I go to /trains'
        ResultActions getTrains = mockMvc.perform(get("/api/trains"))

        then: 'I see all trains'
        getTrains.andExpect(status().isOk())
                .andExpect(content().json("""
                {
                    "content": [
                        {"name":"$inka.name"},
                        {"name":"$awangarda.name"},
                        {"name":"$stanczyk.name"}
                    ]
                }"""))

        when: 'I go to /train/'
        ResultActions getTrain = mockMvc.perform(get("/api/train/$awangarda.name"))

        then: 'I see details of that train'
        getTrain.andExpect(status().isOk())
                .andExpect(content().json("""
                        {"name":"$awangarda.name"}
                """))

        cleanup:
        trainFacade.delete("Inka")
        trainFacade.delete("Awangarda")
        trainFacade.delete("Stańczyk")
    }
}