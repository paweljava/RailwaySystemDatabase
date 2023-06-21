package com.railway.domain

import com.railway.domain.train.SampleTrain
import com.railway.domain.train.TrainFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
class TrainControllerTestIntegration extends Specification implements SampleTrain {

    @Autowired
    WebApplicationContext webApplicationContext

    @Autowired
    TrainFacade trainFacade

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
                .build()
    }

    @WithMockUser
    def "should get trains"() {
        given: 'inventory has "Inka", "Awangarda", "Stańczyk"'
        trainFacade.add(inka)
        trainFacade.add(awangarda)
        trainFacade.add(stanczyk)

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
    }
}