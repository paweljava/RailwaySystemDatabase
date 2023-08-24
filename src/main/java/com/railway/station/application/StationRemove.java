package com.railway.station.application;

import com.railway.station.domain.StationFacade;
import com.railway.station.domain.common.StationId;
import org.springframework.stereotype.Component;

@Component
public class StationRemove {

    private final StationFacade stationFacade;

    StationRemove(StationFacade stationFacade) {
        this.stationFacade = stationFacade;
    }

    public void deleteStation(StationId stationId) {
        stationFacade.deleteStation(stationId);
    }
}
