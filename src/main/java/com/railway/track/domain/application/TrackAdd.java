package com.railway.track.domain.application;

import com.railway.track.domain.TrackFacade;
import com.railway.track.domain.dto.CreateTrackDto;
import com.railway.track.infrastructure.query.TrackQueryDto;
import org.springframework.stereotype.Component;

@Component
public class TrackAdd {

    private final TrackFacade trackFacade;

    TrackAdd(TrackFacade trackFacade) {
        this.trackFacade = trackFacade;
    }

    public TrackQueryDto addTrack(CreateTrackDto createTrackDto) {
        return trackFacade.addTrack(createTrackDto);
    }
}
