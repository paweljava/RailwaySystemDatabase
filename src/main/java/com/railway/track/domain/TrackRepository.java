package com.railway.track.domain;

import com.railway.track.domain.common.TrackId;
import com.railway.track.domain.exception.TrackNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface TrackRepository extends JpaRepository<Track, TrackId> {

    @NonNull
    <S extends Track> S save(@NonNull S track);

    @NonNull
    Page<Track> findAll(@NonNull Pageable pageable);

    @NonNull
    Optional<Track> findById(@NonNull TrackId trackId);

    void delete(@NonNull Track track);

    default Track getByIdOrThrow(TrackId trackId) {
        return findById(trackId).orElseThrow(() -> new TrackNotFoundException(trackId));
    }

    void deleteAll();
}
