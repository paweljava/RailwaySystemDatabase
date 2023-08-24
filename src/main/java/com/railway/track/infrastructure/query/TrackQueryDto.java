package com.railway.track.infrastructure.query;

import com.railway.station.domain.common.StationId;
import com.railway.track.domain.common.TrackId;
import com.railway.train.domain.common.TrainId;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Builder
public record TrackQueryDto(TrackId trackId, TrainId trainId, StationId sourceStationId, StationId destinationStationId,
                            LocalTime timeIn, LocalTime timeOut) {
}
