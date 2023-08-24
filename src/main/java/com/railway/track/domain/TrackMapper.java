package com.railway.track.domain;

import com.railway.track.infrastructure.query.TrackQueryDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

class TrackMapper {

    List<TrackQueryDto> map(List<Track> track) {
        return track.stream().map(
                        city -> new TrackQueryDto(
                                city.getTrackId(),
                                city.getTrainId(),
                                city.getSourceStationId(),
                                city.getDestinationStationId(),
                                city.getTimeIn(),
                                city.getTimeOut()))
                .collect(toList());
    }
}
