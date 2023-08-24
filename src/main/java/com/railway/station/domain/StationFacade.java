package com.railway.station.domain;

import com.railway.station.domain.common.StationId;
import com.railway.station.domain.dto.CreateStationDto;
import com.railway.station.infrastructure.query.StationQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static java.util.Objects.requireNonNull;

public class StationFacade {

    private final StationRepository stationRepository;
    private final StationCreator stationCreator;

    public StationFacade(StationRepository stationRepository, StationCreator stationCreator) {
        this.stationRepository = stationRepository;
        this.stationCreator = stationCreator;
    }

    public StationQueryDto addStation(CreateStationDto createStationDto) {
        requireNonNull(createStationDto);
        final var station = stationCreator.createStation(createStationDto);
        stationRepository.save(station);
        return station.stationQueryDto();
    }

    public StationQueryDto getStation(StationId stationId) {
        requireNonNull(stationId);
        final var station = stationRepository.getByIdOrThrow(stationId);
        return station.stationQueryDto();
    }

    public Page<StationQueryDto> findAll(Pageable pageable) {
        requireNonNull(pageable);
        return stationRepository
                .findAll(pageable)
                .map(Station::stationQueryDto);
    }

    public void deleteStation(StationId stationId) {
        requireNonNull(stationId);
        stationRepository.deleteById(stationId);
    }
}
