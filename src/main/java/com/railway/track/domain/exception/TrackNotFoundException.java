package com.railway.track.domain.exception;

import com.railway.track.domain.common.TrackId;

public class TrackNotFoundException extends RuntimeException {

    public TrackNotFoundException(TrackId trackId) {
        super("No track of id " + trackId.getValue() + " found");
    }
}