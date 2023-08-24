package com.railway.train.domain;

import com.railway.train.domain.common.TrainId;
import com.railway.train.domain.dto.CreateTrainDto;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

class TrainCreator {

    Train createTrain(CreateTrainDto createTrainDto) {
        requireNonNull(createTrainDto);
        return new Train(TrainId.of(UUID.randomUUID()), createTrainDto.trainName());
    }
}
