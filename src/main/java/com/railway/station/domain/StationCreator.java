package com.railway.station.domain;

import com.railway.station.domain.common.StationId;
import com.railway.station.domain.dto.CreateStationDto;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

class StationCreator {

    Station createStation(CreateStationDto createStationDto) {
        requireNonNull(createStationDto);
        return new Station(
                StationId.of(UUID.randomUUID()),
                createStationDto.stationName(),
                createStationDto.stationAddress());
    }
}
