package com.railway.station.infrastructure

import com.railway.base.IntegrationSpec
import com.railway.station.domain.SampleStations
import com.railway.station.domain.StationConfiguration
import com.railway.station.domain.StationFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions

@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
class StationControllerSpec extends Specification implements SampleStations {

    private final StationFacade stationFacade = new StationConfiguration().stationFacade()

    @Autowired
    MockMvc mockMvc

    @WithMockUser
    def "should get stations"() {
        given: 'inventory has stations: "Gdańsk, Warszawa, Kraków"'
        def stationGdansk = stationFacade.addStation(gdansk)
        def stationWarszawa = stationFacade.addStation(warszawa)
        def stationKrakow = stationFacade.addStation(krakow)

        when: 'I go to /stations'
        ResultActions getStations = mockMvc.perform(get("/api/stations"))

        then: 'I see all stations'
        getStations.andExpect(status().isOk())
                .andExpect(content().json("""
                {
                    "content": [
                        {"stationId":"$stationGdansk.stationId","stationName":"$stationGdansk.stationName","stationAddress":"$stationGdansk.stationAddress"},
                        {"stationId":"$stationWarszawa.stationId","stationName":"$stationWarszawa.stationName","stationAddress":"$stationWarszawa.stationAddress"},
                        {"stationId":"$stationKrakow.stationId","stationName":"$stationKrakow.stationName","stationAddress":"$stationKrakow.stationAddress"}
                    ]
                }"""))

        /*when: 'I go to /station/warszawa'
        ResultActions getStation = mockMvc.perform(get("/api/station/$stationWarszawa.stationId"))

        then: 'I see details of that station'
        getStation.andExpect(status().isOk())
                .andExpect(content().json("""
                        {"stationName":"$stationWarszawa.stationName","stationAddress":"$stationWarszawa.stationAddress"}
                """))

        cleanup:
        stationFacade.deleteStation(stationGdansk.getStationId())
        stationFacade.deleteStation(stationWarszawa.getStationId())
        stationFacade.deleteStation(stationKrakow.getStationId())*/
    }
}

import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

/*@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration")
*//*@ComponentScan(basePackageClasses = [StationConfiguration.class, StationFacade.class])
@SpringJUnitWebConfig*//*
@AutoConfigureMockMvc
@WithMockUser
@WebMvcTest(controllers = StationController)*/

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
