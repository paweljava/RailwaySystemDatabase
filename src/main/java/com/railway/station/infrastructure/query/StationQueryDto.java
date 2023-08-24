package com.railway.station.infrastructure.query;

import com.railway.station.domain.common.StationAddress;
import com.railway.station.domain.common.StationId;
import com.railway.station.domain.common.StationName;
import lombok.Builder;

@Builder
public record StationQueryDto(StationId stationId, StationName stationName, StationAddress stationAddress) {
}
