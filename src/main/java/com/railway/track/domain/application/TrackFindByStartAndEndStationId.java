package com.railway.track.domain.application;

import com.railway.station.domain.common.StationId;
import com.railway.track.domain.TrackFacade;
import com.railway.track.infrastructure.query.TrackQueryDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrackFindByStartAndEndStationId {

    private final TrackFacade trackFacade;

    TrackFindByStartAndEndStationId(TrackFacade trackFacade) {
        this.trackFacade = trackFacade;
    }

    public List<TrackQueryDto> findTracks(StationId startStationId, StationId endStationId) {
        return trackFacade.findTracks(startStationId, endStationId);
    }
}
