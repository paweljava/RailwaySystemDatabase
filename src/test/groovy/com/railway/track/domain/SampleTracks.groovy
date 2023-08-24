package com.railway.track.domain

import com.railway.station.domain.common.StationAddress
import com.railway.station.domain.common.StationId
import com.railway.station.domain.common.StationName
import com.railway.station.domain.dto.CreateStationDto
import com.railway.station.infrastructure.query.StationQueryDto
import com.railway.track.domain.dto.CreateTrackDto
import com.railway.train.domain.common.TrainId
import com.railway.train.domain.common.TrainName
import com.railway.train.infrastructure.query.TrainQueryDto
import groovy.transform.CompileStatic

import java.time.LocalTime

@CompileStatic
trait SampleTracks {

    /*CreateTrainDto inka = createTrainDto(TrainName.of("Inka"))
    CreateTrainDto awangarda = createTrainDto(TrainName.of("Awangarda"))
    CreateTrainDto stanczyk = createTrainDto(TrainName.of("Stańczyk"))

    private CreateTrainDto createTrainDto(TrainName name) {
        return CreateTrainDto.builder()
                .trainName(name)
                .build()
    }*/

    /*CreateStationDto gdansk = createStationDto(StationName.of("Gdańsk"), StationAddress.of("Podwale Grodzkie 2"))
    CreateStationDto warszawa = createStationDto(StationName.of("Warszawa"), StationAddress.of("Al. Jerozolimskie 54"))
    CreateStationDto krakow = createStationDto(StationName.of("Kraków"), StationAddress.of("Ul. Pawia 5a"))

    private CreateStationDto createStationDto(StationName stationName, StationAddress stationAddress) {
        return CreateStationDto.builder()
                .stationName(stationName)
                .stationAddress(stationAddress)
                .build()
    }*/

    TrainQueryDto inka = new TrainQueryDto(TrainId.of(UUID.randomUUID()), TrainName.of("Inka"))
    StationQueryDto stationGdansk = new StationQueryDto(StationId.of(UUID.randomUUID()), StationName.of("Gdańsk"), StationAddress.of("Podwale Grodzkie 2"))
    StationQueryDto stationWarszawa = new StationQueryDto(StationId.of(UUID.randomUUID()), StationName.of("Warszawa"), StationAddress.of("Al. Jerozolimskie 54"))
    StationQueryDto stationKrakow = new StationQueryDto(StationId.of(UUID.randomUUID()), StationName.of("Kraków"), StationAddress.of("Ul. Pawia 5a"))

    CreateTrackDto gdanskWarszawa = createTrackDto(inka.trainId(), stationGdansk.stationId(), stationWarszawa.stationId(), LocalTime.of(6,00), LocalTime.of(8,15))
    CreateTrackDto warszawaKrakow = createTrackDto(inka.trainId(), stationWarszawa.stationId(), stationKrakow.stationId(), LocalTime.of(8,30), LocalTime.of(13,25))
    CreateTrackDto krakowWarszawa = createTrackDto(inka.trainId(), stationKrakow.stationId(), stationWarszawa.stationId(), LocalTime.of(14,05), LocalTime.of(19,00))
    CreateTrackDto warszawaGdansk = createTrackDto(inka.trainId(), stationWarszawa.stationId(), stationGdansk.stationId(), LocalTime.of(19,30), LocalTime.of(21,45))

    private CreateTrackDto createTrackDto(TrainId trainId, StationId sourceStationId, StationId destinationStationId, LocalTime timeIn, LocalTime timeOut) {
        return CreateTrackDto.builder()
                .trainId(trainId)
                .sourceStationId(sourceStationId)
                .destinationStationId(destinationStationId)
                .timeIn(timeIn)
                .timeOut(timeOut)
                .build()
    }
}
