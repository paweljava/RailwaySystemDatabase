package com.railway.track.domain;

import com.railway.track.domain.common.TrackId;
import com.railway.track.domain.dto.CreateTrackDto;

import java.util.UUID;

import static java.util.Objects.requireNonNull;

class TrackCreator {

    Track createTrack(CreateTrackDto createTrackDto) {
        requireNonNull(createTrackDto);
        return Track.builder()
                .trackId(TrackId.of(UUID.randomUUID()))
                .trainId(createTrackDto.getTrainId())
                .sourceStationId(createTrackDto.getSourceStationId())
                .destinationStationId(createTrackDto.getDestinationStationId())
                .timeIn(createTrackDto.getTimeIn())
                .timeOut(createTrackDto.getTimeOut())
                .build();
    }
}