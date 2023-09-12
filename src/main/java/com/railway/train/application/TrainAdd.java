package com.railway.train.application;

import com.railway.train.domain.TrainFacade;
import com.railway.train.infrastructure.query.TrainQueryDto;
import org.springframework.stereotype.Component;

@Component
public class TrainAdd {

    private final TrainFacade trainFacade;

    TrainAdd(TrainFacade trainFacade) {
        this.trainFacade = trainFacade;
    }

    public TrainQueryDto addTrain(com.railway.train.domain.dto.CreateTrainDto createTrainDto) {
        return trainFacade.addTrain(createTrainDto);
    }
}