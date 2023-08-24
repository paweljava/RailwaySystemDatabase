package com.railway.station.application;

import com.railway.station.domain.StationFacade;
import com.railway.station.infrastructure.query.StationQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class StationFindAll {

    private final StationFacade stationFacade;

    StationFindAll(StationFacade stationFacade) {
        this.stationFacade = stationFacade;
    }

    public Page<StationQueryDto> findAll(Pageable pageable) {
        return stationFacade.findAll(pageable);
    }
}
