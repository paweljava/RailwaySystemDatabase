package com.railway.domain.track.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Getter
@Builder
public class CreateTrackDto {

    private final String trainId;
    private final String sourceStationId;
    private final String destinationStationId;
    private final LocalTime timeIn;
    private final LocalTime timeOut;

    public CreateTrackDto(String trainId, String sourceStationId, String destinationStationId, LocalTime timeIn, LocalTime timeOut) {
        this.trainId = trainId;
        this.sourceStationId = sourceStationId;
        this.destinationStationId = destinationStationId;
        this.timeIn = LocalTime.parse(timeIn.plus(2, ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern("HH:mm")));
        this.timeOut = LocalTime.parse(timeOut.plus(2, ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}

