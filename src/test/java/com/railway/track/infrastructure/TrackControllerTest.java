package com.railway.track.infrastructure;

import com.railway.station.domain.StationFacade;
import com.railway.station.domain.common.StationAddress;
import com.railway.station.domain.common.StationName;
import com.railway.station.domain.dto.CreateStationDto;
import com.railway.track.domain.TrackFacade;
import com.railway.track.domain.dto.CreateTrackDto;
import com.railway.train.domain.TrainFacade;
import com.railway.train.domain.common.TrainName;
import com.railway.train.domain.dto.CreateTrainDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static java.time.format.DateTimeFormatter.ofPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@WithMockUser
class TrackControllerTest {

    @Autowired
    private TrackFacade trackFacade;
    @Autowired
    private TrainFacade trainFacade;

    @Autowired
    private StationFacade stationFacade;

    @Autowired
    private MockMvc mockMvc;

    CreateTrainDto inka = new CreateTrainDto(TrainName.of("Inka"));

    CreateStationDto gdansk = new CreateStationDto(StationName.of("Gdańsk"), StationAddress.of("Podwale Grodzkie 2"));
    CreateStationDto warszawa = new CreateStationDto(StationName.of("Warszawa"), StationAddress.of("Al. Jerozolimskie 54"));
    CreateStationDto krakow = new CreateStationDto(StationName.of("Kraków"), StationAddress.of("Ul. Pawia 5a"));

    @Test
    void should_get_track_by_id() throws Exception {
        // given: 'Inventory has tracks: Gdańsk-Warszawa-Kraków-Warszawa-Gdańsk'
        final var trainInka = trainFacade.addTrain(inka);
        final var stationGdansk = stationFacade.addStation(gdansk);
        final var stationWarszawa = stationFacade.addStation(warszawa);
        final var stationKrakow = stationFacade.addStation(krakow);
        final var gdanskWarszawa = new CreateTrackDto(trainInka.trainId(), stationGdansk.stationId(), stationWarszawa.stationId(), LocalTime.of(6, 10), LocalTime.of(8, 15));
        final var warszawaKrakow = new CreateTrackDto(trainInka.trainId(), stationWarszawa.stationId(), stationKrakow.stationId(), LocalTime.of(6, 0), LocalTime.of(8, 15));
        final var krakowWarszawa = new CreateTrackDto(trainInka.trainId(), stationKrakow.stationId(), stationWarszawa.stationId(), LocalTime.of(6, 0), LocalTime.of(8, 15));
        final var warszawaGdansk = new CreateTrackDto(trainInka.trainId(), stationWarszawa.stationId(), stationGdansk.stationId(), LocalTime.of(6, 0), LocalTime.of(8, 15));

        final var trackGdanskWarszawa = trackFacade.addTrack(gdanskWarszawa);
        final var trackWarszawaKrakow = trackFacade.addTrack(warszawaKrakow);
        final var trackKrakowWarszawa = trackFacade.addTrack(krakowWarszawa);
        final var trackWarszawaGdansk = trackFacade.addTrack(warszawaGdansk);

        // when: 'I go to /track/{trackId}'
        final var getTrack = mockMvc.perform(get("/api/track")
                .queryParam("id", trackWarszawaKrakow.trackId().getValue().toString()));

        // then: 'I see details of that track'
        getTrack.andExpect(status()
                        .isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                            {
                                "trackId": {"value": "%s"},
                                "trainId": {"value": "%s"},
                                "sourceStationId": {"value": "%s"},
                                "destinationStationId": {"value": "%s"},
                                "timeIn": "%s",
                                "timeOut": "%s"
                            }
                        """.formatted(trackWarszawaKrakow.trackId().getValue(),
                        trackWarszawaKrakow.trainId().getValue(),
                        trackWarszawaKrakow.sourceStationId().getValue(),
                        trackWarszawaKrakow.destinationStationId().getValue(),
                        trackWarszawaKrakow.timeIn().format(ofPattern("HH:mm:ss")),
                        trackWarszawaKrakow.timeOut().format(ofPattern("HH:mm:ss")))));

        trackFacade.delete(trackGdanskWarszawa.trackId());
        trackFacade.delete(trackWarszawaKrakow.trackId());
        trackFacade.delete(trackKrakowWarszawa.trackId());
        trackFacade.delete(trackWarszawaGdansk.trackId());

    }

    @Test
    void should_get_all_tracks() throws Exception {
        // given 'Inventory has tracks: Gdańsk-Warszawa-Kraków-Warszawa-Gdańsk'
        final var trainInka = trainFacade.addTrain(inka);
        final var stationGdansk = stationFacade.addStation(gdansk);
        final var stationWarszawa = stationFacade.addStation(warszawa);
        final var stationKrakow = stationFacade.addStation(krakow);
        final var gdanskWarszawa = new CreateTrackDto(trainInka.trainId(), stationGdansk.stationId(), stationWarszawa.stationId(), LocalTime.of(6, 10), LocalTime.of(8, 15));
        final var warszawaKrakow = new CreateTrackDto(trainInka.trainId(), stationWarszawa.stationId(), stationKrakow.stationId(), LocalTime.of(6, 0), LocalTime.of(8, 15));
        final var krakowWarszawa = new CreateTrackDto(trainInka.trainId(), stationKrakow.stationId(), stationWarszawa.stationId(), LocalTime.of(6, 0), LocalTime.of(8, 15));
        final var warszawaGdansk = new CreateTrackDto(trainInka.trainId(), stationWarszawa.stationId(), stationGdansk.stationId(), LocalTime.of(6, 0), LocalTime.of(8, 15));
        final var trackGdanskWarszawa = trackFacade.addTrack(gdanskWarszawa);
        final var trackWarszawaKrakow = trackFacade.addTrack(warszawaKrakow);
        final var trackKrakowWarszawa = trackFacade.addTrack(krakowWarszawa);
        final var trackWarszawaGdansk = trackFacade.addTrack(warszawaGdansk);
        final var formatter = ofPattern("HH:mm:ss");

        //when 'I go to /tracks'
        final var getTracks = mockMvc.perform(get("/api/tracks"));

        // then: 'I see all tracks'
        getTracks.andExpect(status()
                        .isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("""
                                {
                            "content": [
                            {
                                    "trackId": {"value": "%s"},
                                    "trainId": {"value": "%s"},
                                    "sourceStationId": {"value": "%s"},
                                    "destinationStationId": {"value": "%s"},
                                    "timeIn": "%s",
                                    "timeOut": "%s"
                                },
                                {
                                    "trackId": {"value": "%s"},
                                    "trainId": {"value": "%s"},
                                    "sourceStationId": {"value": "%s"},
                                    "destinationStationId": {"value": "%s"},
                                    "timeIn": "%s",
                                    "timeOut": "%s"
                                },
                                {
                                    "trackId": {"value": "%s"},
                                    "trainId": {"value": "%s"},
                                    "sourceStationId": {"value": "%s"},
                                    "destinationStationId": {"value": "%s"},
                                    "timeIn": "%s",
                                    "timeOut": "%s"
                                },
                                {
                                    "trackId": {"value": "%s"},
                                    "trainId": {"value": "%s"},
                                    "sourceStationId": {"value": "%s"},
                                    "destinationStationId": {"value": "%s"},
                                    "timeIn": "%s",
                                    "timeOut": "%s"
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
                            "totalElements": 4,
                            "last": true,
                            "size": 20,
                            "number": 0,
                            "sort": {"empty": true, "unsorted": true, "sorted": false},
                            "numberOfElements": 4,
                            "first": true,
                            "empty": false
                        }
                            """.formatted(
                        trackGdanskWarszawa.trackId().getValue(),
                        trackGdanskWarszawa.trainId().getValue(),
                        trackGdanskWarszawa.sourceStationId().getValue(),
                        trackGdanskWarszawa.destinationStationId().getValue(),
                        trackGdanskWarszawa.timeIn().format(formatter),
                        trackGdanskWarszawa.timeOut().format(formatter),

                        trackWarszawaKrakow.trackId().getValue(),
                        trackWarszawaKrakow.trainId().getValue(),
                        trackWarszawaKrakow.sourceStationId().getValue(),
                        trackWarszawaKrakow.destinationStationId().getValue(),
                        trackWarszawaKrakow.timeIn().format(formatter),
                        trackWarszawaKrakow.timeOut().format(formatter),

                        trackKrakowWarszawa.trackId().getValue(),
                        trackKrakowWarszawa.trainId().getValue(),
                        trackKrakowWarszawa.sourceStationId().getValue(),
                        trackKrakowWarszawa.destinationStationId().getValue(),
                        trackKrakowWarszawa.timeIn().format(formatter),
                        trackKrakowWarszawa.timeOut().format(formatter),

                        trackWarszawaGdansk.trackId().getValue(),
                        trackWarszawaGdansk.trainId().getValue(),
                        trackWarszawaGdansk.sourceStationId().getValue(),
                        trackWarszawaGdansk.destinationStationId().getValue(),
                        trackWarszawaGdansk.timeIn().format(formatter),
                        trackWarszawaGdansk.timeOut().format(formatter))));

        trackFacade.delete(trackGdanskWarszawa.trackId());
        trackFacade.delete(trackWarszawaKrakow.trackId());
        trackFacade.delete(trackKrakowWarszawa.trackId());
        trackFacade.delete(trackWarszawaGdansk.trackId());
    }
}