package com.railway.station.application;

import com.railway.station.domain.StationFacade;
import com.railway.station.domain.dto.CreateStationDto;
import com.railway.station.infrastructure.query.StationQueryDto;
import org.springframework.stereotype.Component;

@Component
public class StationAdd {

    private final StationFacade stationFacade;

    StationAdd(StationFacade stationFacade) {
        this.stationFacade = stationFacade;
    }

    public StationQueryDto addStation(CreateStationDto createStationDto) {
        return stationFacade.addStation(createStationDto);
    }
}