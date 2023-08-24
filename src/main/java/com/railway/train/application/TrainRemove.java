package com.railway.train.application;

import com.railway.train.domain.TrainFacade;
import com.railway.train.domain.common.TrainId;
import org.springframework.stereotype.Component;

@Component
public class TrainRemove {

    private final TrainFacade trainFacade;

    TrainRemove(TrainFacade trainFacade) {
        this.trainFacade = trainFacade;
    }

    public void deleteTrain(TrainId trainId) {
        trainFacade.delete(trainId);
    }
}
