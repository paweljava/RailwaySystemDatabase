package com.railway.train.infrastructure;

import com.railway.train.domain.TrainFacade;
import com.railway.train.domain.common.TrainName;
import com.railway.train.domain.dto.CreateTrainDto;
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
    private TrainFacade trainFacade;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_get_train_by_id() throws Exception {
        // given: 'Inventory has trains: Inka, Awangarda, Stańczyk'
        final var trainInka = trainFacade.addTrain(new CreateTrainDto(TrainName.of("Inka")));
        final var trainAwangarda = trainFacade.addTrain(new CreateTrainDto(TrainName.of("Awangarda")));
        final var trainStanczyk = trainFacade.addTrain(new CreateTrainDto(TrainName.of("Stańczyk")));

        // when: 'I go to /station/{stationId}'
        final var getTrain = mockMvc.perform(get("/api/train/{trainId}",
                trainAwangarda.trainId().getValue()));

        // then: 'I see details of that train'
        getTrain.andExpect(status().isOk()).andExpect(content().json("""
                    {
                        "trainId": {"value": "%s"},
                        "trainName": {"value": "%s"}
                    }
                """.formatted(trainAwangarda.trainId().getValue(),
                trainAwangarda.trainName().getValue())));

        /*trainFacade.delete(trainAwangarda.trainId());
        trainFacade.delete(trainInka.trainId());
        trainFacade.delete(trainStanczyk.trainId());*/
    }

    @Test
    void should_get_all_trains() throws Exception {
        // given 'Inventory has trains: "Inka, Awangarda, Stańczyk"'
        final var trainInka = trainFacade.addTrain(new CreateTrainDto(TrainName.of("Inka")));
        final var trainAwangarda = trainFacade.addTrain(new CreateTrainDto(TrainName.of("Awangarda")));
        final var trainStanczyk = trainFacade.addTrain(new CreateTrainDto(TrainName.of("Stańczyk")));

        // when 'I go to /trains'
        final var getTrains = mockMvc.perform(get("/api/trains"));

        // then: 'I see all trains'
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
}