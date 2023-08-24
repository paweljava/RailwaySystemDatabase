package com.railway.train.application;

import com.railway.train.domain.TrainFacade;
import com.railway.train.domain.common.TrainId;
import com.railway.train.infrastructure.query.TrainQueryDto;
import org.springframework.stereotype.Component;

@Component
public class TrainFindByTrainId {

    private final TrainFacade trainFacade;

    TrainFindByTrainId(TrainFacade trainFacade) {
        this.trainFacade = trainFacade;
    }

    public TrainQueryDto getTrain(TrainId trainId) {
        return trainFacade.getTrain(trainId);
    }
}
