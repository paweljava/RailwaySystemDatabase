package com.railway.station.infrastructure;

import com.railway.station.application.StationAdd;
import com.railway.station.application.StationFindAll;
import com.railway.station.application.StationFindByStationId;
import com.railway.station.application.StationRemove;
import com.railway.station.domain.common.StationId;
import com.railway.station.domain.dto.CreateStationDto;
import com.railway.station.infrastructure.query.StationQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
class StationController {

    private final StationAdd stationAdd;
    private final StationFindAll stationFindAll;
    private final StationFindByStationId stationFindByStationId;
    private final StationRemove stationRemove;

    StationController(StationAdd stationAdd, StationFindAll stationFindAll, StationFindByStationId stationFindByStationId, StationRemove stationRemove) {
        this.stationAdd = stationAdd;
        this.stationFindAll = stationFindAll;
        this.stationFindByStationId = stationFindByStationId;
        this.stationRemove = stationRemove;
    }

    @GetMapping("/stations")
    Page<StationQueryDto> getStations(Pageable pageable) {
        return stationFindAll.findAll(pageable);
    }

    @GetMapping("/station/{id}")
    StationQueryDto getStation(@PathVariable UUID id) {
        return stationFindByStationId.getStation(StationId.of(id));
    }

    @PostMapping("/station/add")
    StationQueryDto createStation(@RequestBody CreateStationDto createStationDto) {
        return stationAdd.addStation(createStationDto);
    }

    @DeleteMapping("/station/{id}")
    void deleteStation(@PathVariable UUID id) {
        stationRemove.deleteStation(StationId.of(id));
    }
}
