package com.railway.train.domain.dto;

import com.railway.train.domain.common.TrainName;
import lombok.Builder;
import lombok.Getter;

@Builder
public record CreateTrainDto(TrainName trainName) {
}
