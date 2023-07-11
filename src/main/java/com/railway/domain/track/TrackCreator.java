package com.railway.domain.track;

import com.railway.domain.track.dto.CreateTrackDto;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

class TrackCreator {

    Track createTrack(CreateTrackDto createTrackDto) {
        requireNonNull(createTrackDto);
        return Track.builder()
                .id(UUID.randomUUID().toString())
                .trainId(createTrackDto.getTrainId())
                .sourceStationId(createTrackDto.getSourceStationId())
                .destinationStationId(createTrackDto.getDestinationStationId())
                .timeIn(createTrackDto.getTimeIn())
                .timeOut(createTrackDto.getTimeOut())
                .build();
    }
}
