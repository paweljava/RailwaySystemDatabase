package com.railway.track.domain.application;

import com.railway.track.domain.TrackFacade;
import com.railway.track.domain.common.TrackId;
import com.railway.track.infrastructure.query.TrackQueryDto;
import org.springframework.stereotype.Component;

@Component
public class TrackFindByTrackId {

    private final TrackFacade trackFacade;

    TrackFindByTrackId(TrackFacade trackFacade) {
        this.trackFacade = trackFacade;
    }

    public TrackQueryDto findTrackById(TrackId trackId) {
        return trackFacade.findTrackById(trackId);
    }
}
