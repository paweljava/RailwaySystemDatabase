package com.railway.domain.station;

import com.railway.domain.station.dto.CreateStationDto;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

class StationCreator {
    Station createStation(CreateStationDto createStationDto) {
        requireNonNull(createStationDto);
        return Station.builder()
                .id(UUID.randomUUID().toString())
                .name(createStationDto.getName())
                .address(createStationDto.getAddress())
                .build();
    }
}
