package com.railway.track.domain.dto;

import com.railway.station.domain.common.StationId;
import com.railway.train.domain.common.TrainId;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class CreateTrackDto {

    private final TrainId trainId;
    private final StationId sourceStationId;
    private final StationId destinationStationId;
    private final LocalTime timeIn;
    private final LocalTime timeOut;

    public CreateTrackDto(TrainId trainId, StationId sourceStationId, StationId destinationStationId, LocalTime timeIn, LocalTime timeOut) {
        this.trainId = trainId;
        this.sourceStationId = sourceStationId;
        this.destinationStationId = destinationStationId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }
}

