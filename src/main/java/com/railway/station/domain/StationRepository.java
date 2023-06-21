package com.railway.station.domain;

import com.railway.station.dto.StationNotFoundException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StationRepository extends MongoRepository<Station, String> {
    Station save(Station station);
    Station findByName(@NonNull String name);
    Page<Station> findAll(Pageable pageable);

    default Station findOneOrThrow(String name) {
        final var station = findByName(name);
        if (station == null) {
            throw new StationNotFoundException(name);
        }
        return station;
    }

    /*void delete(String name);*/
}