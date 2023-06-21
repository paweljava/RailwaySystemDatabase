package com.railway.station.domain

import com.railway.station.dto.StationDto
import groovy.transform.CompileStatic

@CompileStatic
trait SampleStations {

    StationDto gdansk = createStationDto("Gdańsk", "Podwale Grodzkie 2")
    StationDto warszawa = createStationDto("Warszawa", "Al. Jerozolimskie 54")
    StationDto krakow = createStationDto("Kraków", "Ul. Pawia 5a")

     private StationDto createStationDto(String name, String address) {
        return StationDto.builder()
                .name(name)
                .address(address)
                .build()
    }
}
