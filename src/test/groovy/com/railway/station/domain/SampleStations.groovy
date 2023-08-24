package com.railway.station.domain

import com.railway.station.domain.common.StationAddress
import com.railway.station.domain.common.StationName
import com.railway.station.domain.dto.CreateStationDto
import groovy.transform.CompileStatic

@CompileStatic
trait SampleStations {

    /*StationName stationName1 = StationName.of("Gdańsk")
    StationAddress stationAddress1 = StationAddress.of("Podwale Grodzkie 2")
    StationName stationName2 = StationName.of("Warszawa")
    StationAddress stationAddress2 = StationAddress.of("Al. Jerozolimskie 54")
    StationName stationName3 = StationName.of("Kraków")
    StationAddress stationAddress3 = StationAddress.of("Ul. Pawia 5a")

    CreateStationDto gdansk = new CreateStationDto(stationName1, stationAddress1)
    CreateStationDto warszawa = new CreateStationDto(stationName2, stationAddress2)
    CreateStationDto krakow = new CreateStationDto(stationName3, stationAddress3)*/

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
