package com.railway.track.domain.application;

import com.railway.track.domain.TrackFacade;
import com.railway.track.domain.common.TrackId;
import org.springframework.stereotype.Component;

@Component
public class TrackRemove {

    private final TrackFacade trackFacade;

    TrackRemove(TrackFacade trackFacade) {
        this.trackFacade = trackFacade;
    }

    public void delete(TrackId trackId) {
        trackFacade.delete(trackId);
    }
}
