package com.railway.station.application;

import com.railway.station.domain.StationFacade;
import com.railway.station.domain.common.StationId;
import com.railway.station.infrastructure.query.StationQueryDto;
import org.springframework.stereotype.Component;

@Component
public class StationFindByStationId {

    private final StationFacade stationFacade;

    StationFindByStationId(StationFacade stationFacade) {
        this.stationFacade = stationFacade;
    }

    public StationQueryDto getStation(StationId stationId) {
        return stationFacade.getStation(stationId);
    }
}
