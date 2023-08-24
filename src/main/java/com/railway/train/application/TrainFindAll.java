package com.railway.train.application;

import com.railway.train.domain.TrainFacade;
import com.railway.train.infrastructure.query.TrainQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TrainFindAll {

    private final TrainFacade trainFacade;

    TrainFindAll(TrainFacade trainFacade) {
        this.trainFacade = trainFacade;
    }

    public Page<TrainQueryDto> findAll(Pageable pageable) {
        return trainFacade.findAll(pageable);
    }
}
