package com.railway.domain.track;

import com.railway.domain.track.dto.TrackQueryDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

class TrackMapper {

    static List<TrackQueryDto> mapper(List<Track> track) {
        return track.stream().map(
                        city -> new TrackQueryDto(
                                city.getSourceStationId(),
                                city.getDestinationStationId(),
                                city.getTimeIn(),
                                city.getTimeOut()))
                .collect(toList());
    }
}
