package com.railway.domain.train;

import com.railway.domain.train.dto.TrainDto;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

class TrainCreator {
    Train from(TrainDto trainDto) {
        requireNonNull(trainDto);
        return Train.builder()
                .id(UUID.randomUUID().toString())
                .name(trainDto.getName())
                .build();
    }
}
