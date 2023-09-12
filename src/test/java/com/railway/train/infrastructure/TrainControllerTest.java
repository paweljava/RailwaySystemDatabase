package com.railway.train.infrastructure;

import com.railway.station.domain.common.StationAddress;
import com.railway.station.domain.common.StationName;
import com.railway.station.domain.dto.CreateStationDto;
import com.railway.train.domain.TrainFacade;
import com.railway.train.domain.common.TrainName;
import com.railway.train.domain.dto.CreateTrainDto;
import com.railway.train.infrastructure.query.TrainQueryDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TrainFacade trainFacade;

    private CreateTrainDto inka;
    private CreateTrainDto awangarda;
    private CreateTrainDto stanczyk;

    private TrainQueryDto trainInka;
    private TrainQueryDto trainAwangarda;
    private TrainQueryDto trainStanczyk;

    @BeforeEach
    void setUp() {
        inka = new CreateTrainDto(TrainName.of("Inka"));
        awangarda = new CreateTrainDto(TrainName.of("Awangarda"));
        stanczyk = new CreateTrainDto(TrainName.of("Stańczyk"));
        setupInitialData();
    }

    @AfterEach
    void cleanupData() {
        trainFacade.delete(trainInka.trainId());
        trainFacade.delete(trainStanczyk.trainId());
        trainFacade.delete(trainAwangarda.trainId());
    }

    @Test
    void should_get_train_by_id() throws Exception {
        // given
        // when
        final var getTrain = mockMvc.perform(get("/api/train/{trainId}",
                trainAwangarda.trainId().getValue()));

        // then
        getTrain.andExpect(status().isOk()).andExpect(content().json("""
                    {
                        "trainId": {"value": "%s"},
                        "trainName": {"value": "%s"}
                    }
                """.formatted(trainAwangarda.trainId().getValue(),
                trainAwangarda.trainName().getValue())));
    }

    @Test
    void should_get_all_trains() throws Exception {
        // given
        // when
        final var getTrains = mockMvc.perform(get("/api/trains"));

        // then
        getTrains.andExpect(status().isOk()).andExpect(content().json("""
                                {
                        "content": [
                            {
                                "trainId": {"value": "%s"},
                                "trainName": {"value": "%s"}
                            },
                            {
                                "trainId": {"value": "%s"},
                                "trainName": {"value": "%s"}
                            },
                            {
                                "trainId": {"value": "%s"},
                                "trainName": {"value": "%s"}
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
                """.formatted(trainInka.trainId().getValue(),
                trainInka.trainName().getValue(),
                trainAwangarda.trainId().getValue(),
                trainAwangarda.trainName().getValue(),
                trainStanczyk.trainId().getValue(),
                trainStanczyk.trainName().getValue())));
    }

    private void setupInitialData() {
        trainInka = trainFacade.addTrain(inka);
        trainAwangarda = trainFacade.addTrain(awangarda);
        trainStanczyk = trainFacade.addTrain(stanczyk);
    }
}