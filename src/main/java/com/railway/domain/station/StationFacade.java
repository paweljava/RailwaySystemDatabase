package com.railway.domain.station;

import com.railway.domain.station.dto.CreateStationDto;
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

    public CreateStationDto add(CreateStationDto createStationDto) {
        requireNonNull(createStationDto);
        final var station = stationCreator.createStation(createStationDto);
        stationRepository.save(station);
        return station.dto();
    }

    public CreateStationDto show(String name) {
        requireNonNull(name);
        final var station = stationRepository.findOneOrThrow(name);
        return station.dto();
    }

    public Page<CreateStationDto> findAll(Pageable pageable) {
        requireNonNull(pageable);
        return stationRepository
                .findAll(pageable)
                .map(Station::dto);
    }

    public void delete(String name) {
        requireNonNull(name);
        stationRepository.delete(stationRepository.findByName(name));
    }
}
