package com.railway.domain.station.dto;

public class StationNotFoundException extends RuntimeException{

    public StationNotFoundException(String id) {
        super("No station of id " + id + " found");
    }
}