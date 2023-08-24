package com.railway.train.infrastructure.query;

import com.railway.train.domain.common.TrainId;
import com.railway.train.domain.common.TrainName;
import lombok.Builder;

@Builder
public record TrainQueryDto(TrainId trainId, TrainName trainName) {
}