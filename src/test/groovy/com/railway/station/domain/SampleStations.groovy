package com.railway.station.domain

import com.railway.station.domain.common.StationAddress
import com.railway.station.domain.common.StationName
import com.railway.station.domain.dto.CreateStationDto
import groovy.transform.CompileStatic

@CompileStatic
trait SampleStations {

    CreateStationDto gdansk = createStationDto(StationName.of("Gdańsk"), StationAddress.of("Podwale Grodzkie 2"))
    CreateStationDto warszawa = createStationDto(StationName.of("Warszawa"), StationAddress.of("Al. Jerozolimskie 54"))
    CreateStationDto krakow = createStationDto(StationName.of("Kraków"), StationAddress.of("Ul. Pawia 5a"))

    private CreateStationDto createStationDto(StationName stationName, StationAddress stationAddress) {
        return CreateStationDto.builder()
                .stationName(stationName)
                .stationAddress(stationAddress)
                .build()
    }
}
