package com.railway.station


import com.railway.station.domain.SampleStations
import com.railway.station.domain.StationFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.annotation.Rollback
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
class RailwayControllerTestIntegration extends Specification implements SampleStations {

    @Autowired
    WebApplicationContext webApplicationContext

    @Autowired
    StationFacade stationFacade

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
                .build()
    }

    @WithMockUser
    def "should get stations"() {
        given: 'inventory has "Gdańsk", "Warszawa", "Kraków"'
        stationFacade.add(gdansk)
        stationFacade.add(warszawa)
        stationFacade.add(krakow)

        when: 'I go to /stations'
        ResultActions getStations = mockMvc.perform(get("/api/stations"))

        then: 'I see all stations'
        getStations.andExpect(status().isOk())
                .andExpect(content().json("""
                {
                    "content": [
                        {"name":"$gdansk.name","address":"$gdansk.address"},
                        {"name":"$warszawa.name","address":"$warszawa.address"},
                        {"name":"$krakow.name","address":"$krakow.address"}
                    ]
                }"""))

        when: 'I go to /station/'
        ResultActions getStation = mockMvc.perform(get("/api/station/$warszawa.name"))

        then: 'I see details of that station'
        getStation.andExpect(status().isOk())
                .andExpect(content().json("""
                        {"name":"$warszawa.name","address":"$warszawa.address"}
                """))
    }
}