package com.railway.track.domain;

import com.railway.common.ImplementMePleaseException;
import com.railway.track.domain.common.TrackId;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

class InMemoryTrackRepository implements TrackRepository {

    private final ConcurrentHashMap<TrackId, Track> database = new ConcurrentHashMap<>();

    @Override
    @NonNull
    public Page<Track> findAll(@NonNull Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(database.values()), pageable, database.size());
    }

    @Override
    @NonNull
    public <S extends Track> S save(@NonNull S track) {
        requireNonNull(track);
        database.putIfAbsent(track.trackQueryDto().trackId(), track);
        return track;
    }

    @Override
    public void deleteById(@NonNull TrackId trackId) {
        database.remove(trackId);
    }

    @Override
    public void delete(@NonNull Track entity) {
        database.remove(entity.trackQueryDto().trackId());
    }

    @Override
    public void deleteAllById(Iterable<? extends TrackId> trackIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAll(Iterable<? extends Track> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public void flush() {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> S saveAndFlush(S entity) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllInBatch(Iterable<Track> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<TrackId> trackIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllInBatch() {
        throw new ImplementMePleaseException();
    }

    @Override
    public Track getOne(TrackId trackId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public Track getById(TrackId trackId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public Track getReferenceById(TrackId trackId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> Optional<S> findOne(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> List<S> findAll(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> List<S> findAll(Example<S> example, Sort sort) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> long count(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> boolean exists(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Track> List<S> saveAll(Iterable<S> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    @NonNull
    public Optional<Track> findById(@NonNull TrackId trackId) {
        return Optional.ofNullable(database.get(trackId));
    }

    @Override
    public boolean existsById(TrackId trackId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public List<Track> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public List<Track> findAllById(Iterable<TrackId> trackIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public long count() {
        throw new ImplementMePleaseException();
    }

    @Override
    public List<Track> findAll(Sort sort) {
        throw new ImplementMePleaseException();
    }
}