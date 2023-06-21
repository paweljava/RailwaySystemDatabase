package com.railway.domain.station;

import com.railway.domain.station.dto.StationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static java.util.Objects.requireNonNull;

public class StationFacade {
    private final StationRepository stationRepository;
    private final StationCreator stationCreator;

    StationFacade(StationRepository stationRepository, StationCreator stationCreator) {
        this.stationRepository = stationRepository;
        this.stationCreator = stationCreator;
    }

    public StationDto add(StationDto stationDto) {
        requireNonNull(stationDto);
        final var station = stationCreator.from(stationDto);
        stationRepository.save(station);
        return station.dto();
    }

    public StationDto show(String name) {
        requireNonNull(name);
        final var station = stationRepository.findOneOrThrow(name);
        return station.dto();
    }

    public Page<StationDto> findAll(Pageable pageable) {
        requireNonNull(pageable);
        return stationRepository
                .findAll(pageable)
                .map(Station::dto);
    }

    /*public void delete(String name) {
        requireNonNull(name);
        stationRepository.delete(name);
    }*/
}
