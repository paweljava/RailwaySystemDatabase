package com.railway.station.domain.exception;

import com.railway.station.domain.common.StationId;

public class StationNotFoundException extends RuntimeException {

    public StationNotFoundException(StationId id) {
        super("No station of id " + id.getValue() + " found");
    }
}