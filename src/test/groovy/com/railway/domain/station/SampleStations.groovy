package com.railway.domain.station

import com.railway.domain.station.dto.CreateStationDto
import groovy.transform.CompileStatic

@CompileStatic
trait SampleStations {

    CreateStationDto gdansk = createStationDto("Gdańsk", "Podwale Grodzkie 2")
    CreateStationDto warszawa = createStationDto("Warszawa", "Al. Jerozolimskie 54")
    CreateStationDto krakow = createStationDto("Kraków", "Ul. Pawia 5a")

     private CreateStationDto createStationDto(String name, String address) {
        return CreateStationDto.builder()
                .name(name)
                .address(address)
                .build()
    }
}
