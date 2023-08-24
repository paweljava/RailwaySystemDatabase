package com.railway.station.infrastructure;

import com.railway.station.domain.StationFacade;
import com.railway.station.domain.common.StationAddress;
import com.railway.station.domain.common.StationName;
import com.railway.station.domain.dto.CreateStationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WithMockUser
class StationControllerTest {

    @Autowired
    private StationFacade stationFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_get_station_by_id() throws Exception {
        // given: 'Inventory has stations: Gdańsk, Warszawa, Kraków'
        final var stationWarszawa = stationFacade.addStation(
                new CreateStationDto(StationName.of("Warszawa"), StationAddress.of("Al. Jerozolimskie 54")));
        stationFacade.addStation(new CreateStationDto(
                StationName.of("Gdańsk"), StationAddress.of("Podwale Grodzkie 2")));
        stationFacade.addStation(new CreateStationDto(
                StationName.of("Kraków"), StationAddress.of("Ul. Pawia 5a")));

        // when: 'I go to /station/{stationId}'
        final var getStation = mockMvc.perform(get("/api/station/{stationId}",
                stationWarszawa.stationId().getValue()));

        // then: 'I see details of that station'
        getStation.andExpect(status().isOk()).andExpect(content().json("""
                    {
                        "stationId": {"value": "%s"},
                        "stationName": {"value": "%s"},
                        "stationAddress": {"value": "%s"}
                    }
                """.formatted(stationWarszawa.stationId().getValue(),
                stationWarszawa.stationName().getValue(),
                stationWarszawa.stationAddress().getValue())));
    }

    @Test
    void should_get_all_stations() throws Exception {

        // given 'Inventory has stations: Gdańsk, Warszawa Kraków'
        final var stationGdansk = stationFacade.addStation(new CreateStationDto(StationName.of("Gdańsk"), StationAddress.of("Podwale Grodzkie 2")));
        final var stationWarszawa = stationFacade.addStation(new CreateStationDto(StationName.of("Warszawa"), StationAddress.of("Al. Jerozolimskie 54")));
        final var stationKrakow = stationFacade.addStation(new CreateStationDto(StationName.of("Kraków"), StationAddress.of("Ul. Pawia 5a")));

        //when 'I go to /stations'
        final var getStations = mockMvc.perform(get("/api/stations"));

        //then: 'I see all stations'
        getStations.andExpect(status().isOk()).andExpect(content().json("""
                                {
                        "content": [
                            {
                                "stationId": {"value": "%s"},
                                "stationName": {"value": "%s"},
                                "stationAddress": {"value": "%s"}
                            },
                            {
                                "stationId": {"value": "%s"},
                                "stationName": {"value": "%s"},
                                "stationAddress": {"value": "%s"}
                            },
                            {
                                "stationId": {"value": "%s"},
                                "stationName": {"value": "%s"},
                                "stationAddress": {"value": "%s"}
                            }
                        ],
                        "pageable": {
                            "sort": {"empty": true, "unsorted": true, "sorted": false},
                            "offset": 0,
                            "pageNumber": 0,
                            "pageSize": 20,
                            "unpaged": false,
                            "paged": true
                        },
                        "totalPages": 1,
                        "totalElements": 3,
                        "last": true,
                        "size": 20,
                        "number": 0,
                        "sort": {"empty": true, "unsorted": true, "sorted": false},
                        "numberOfElements": 3,
                        "first": true,
                        "empty": false
                    }
                """.formatted(stationGdansk.stationId().getValue(),
                stationGdansk.stationName().getValue(),
                stationGdansk.stationAddress().getValue(),
                stationWarszawa.stationId().getValue(),
                stationWarszawa.stationName().getValue(),
                stationWarszawa.stationAddress().getValue(),
                stationKrakow.stationId().getValue(),
                stationKrakow.stationName().getValue(),
                stationKrakow.stationAddress().getValue())));
    }
}

