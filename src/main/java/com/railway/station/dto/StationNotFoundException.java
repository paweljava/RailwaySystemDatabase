package com.railway.station.dto;

public class StationNotFoundException extends RuntimeException{
    public StationNotFoundException(String id) {
        super("No station of name " + id + " found");
    }
}