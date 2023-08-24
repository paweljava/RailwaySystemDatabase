package com.railway.station.domain.dto;

import com.railway.station.domain.common.StationAddress;
import com.railway.station.domain.common.StationName;
import lombok.Builder;

@Builder
public record CreateStationDto(StationName stationName, StationAddress stationAddress) {

}
