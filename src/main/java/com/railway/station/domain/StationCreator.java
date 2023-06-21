package com.railway.station.domain;

import com.railway.station.dto.StationDto;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

class StationCreator {
    Station from(StationDto stationDto) {
        requireNonNull(stationDto);
        return Station.builder()
                .id(UUID.randomUUID().toString())
                .name(stationDto.getName())
                .address(stationDto.getAddress())
                .build();
    }
}
