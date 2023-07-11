package com.railway.domain.station;

import com.railway.domain.station.dto.StationNotFoundException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

interface StationRepository extends MongoRepository<Station, String> {
    Station save(Station station);

    Station findByName(@NonNull String name);

    Page<Station> findAll(Pageable pageable);

    void delete(Station entity);

    default Station findOneOrThrow(String name) {
        final var station = findByName(name);
        if (station == null) {
            throw new StationNotFoundException(name);
        }
        return station;
    }

    /*void delete(String name);*/
}