package com.railway.track.domain.application;

import com.railway.track.domain.TrackFacade;
import com.railway.track.infrastructure.query.TrackQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TrackFindAll {

    private final TrackFacade trackFacade;

    TrackFindAll(TrackFacade trackFacade) {
        this.trackFacade = trackFacade;
    }

    public Page<TrackQueryDto> findAll(Pageable pageable) {
        return trackFacade.findAll(pageable);
    }
}
