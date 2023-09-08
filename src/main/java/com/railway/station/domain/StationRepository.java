package com.railway.station.domain;

import com.railway.station.domain.common.StationId;
import com.railway.station.domain.exception.StationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

interface StationRepository extends JpaRepository<Station, StationId> {

    @NonNull
    <S extends Station> S save(@NonNull S station);

    @NonNull
    Optional<Station> findById(@NonNull StationId stationId);

    @NonNull
    Page<Station> findAll(@NonNull Pageable pageable);

    @NonNull
    void delete(@NonNull Station entity);

    void deleteAll();

    default Station getByIdOrThrow(StationId stationId) {
        return findById(stationId).orElseThrow(() -> new StationNotFoundException(stationId));
    }
}