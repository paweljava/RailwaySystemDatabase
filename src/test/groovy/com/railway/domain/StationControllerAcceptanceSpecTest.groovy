package com.railway.domain

import com.railway.base.IntegrationSpec
import com.railway.domain.station.SampleStations
import com.railway.domain.station.StationFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class StationControllerAcceptanceSpecTest extends IntegrationSpec implements SampleStations {

    @Autowired
    StationFacade stationFacade

    @BeforeTestClass
    void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build()
    }

    @WithMockUser
    def "should get stations"() {
        given: 'inventory has "Gdańsk", "Warszawa", "Kraków"'
        stationFacade.add(gdansk)
        stationFacade.add(warszawa)
        stationFacade.add(krakow)

        when: 'I go to /stations'
        ResultActions getStations = mockMvc.perform(get("/stations"))

        then: 'I see all stations'
        getStations.andExpect(status().isOk())
                .andExpect(content().json("""
                {
                    "content": [
                        {"name":"$gdansk.name","type":"$gdansk.address"},
                        {"name":"$warszawa.name","type":"$warszawa.address"}
                        {"name":"$krakow.name","type":"$krakow.address"}
                    ]
                }"""))

        when: 'I go to /station/'
        ResultActions getStation = mockMvc.perform(get("/station/$warszawa.name"))

        then: 'I see details of that station'
        getStation.andExpect(status().isOk())
                .andExpect(content().json("""
                        {"name":"$warszawa.name","address":"$warszawa.address"},
                """))
    }
}
