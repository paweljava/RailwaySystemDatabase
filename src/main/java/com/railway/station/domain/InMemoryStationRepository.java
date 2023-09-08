package com.railway.station.domain;

import com.railway.common.ImplementMePleaseException;
import com.railway.station.domain.common.StationId;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

class InMemoryStationRepository implements StationRepository {
    private final ConcurrentHashMap<StationId, Station> database = new ConcurrentHashMap<>();

    @Override
    @NonNull
    public <S extends Station> S save(@NonNull S station) {
        requireNonNull(station);
        database.putIfAbsent(station.stationQueryDto().stationId(), station);
        return station;
    }

    @Override
    public void flush() {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station> S saveAndFlush(S entity) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllInBatch(Iterable<Station> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<StationId> stationIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllInBatch() {
        throw new ImplementMePleaseException();
    }

    @Override
    public Station getOne(StationId stationId) {
        throw new ImplementMePleaseException();
    }

    @Override
    @NonNull
    public Station getById(@NonNull StationId stationId) {
        Station station = database.get(stationId);
        if (station == null) {
            throw new EntityNotFoundException();
        }
        return station;
    }

    @Override
    public Station getReferenceById(StationId stationId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station> Optional<S> findOne(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station> List<S> findAll(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station> List<S> findAll(Example<S> example, Sort sort) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station> long count(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station> boolean exists(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Station, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new ImplementMePleaseException();
    }

    @Override
    @NonNull
    public Optional<Station> findById(@NonNull StationId stationId) {
        return Optional.ofNullable(database.get(stationId));
    }

    @Override
    public boolean existsById(StationId stationId) {
        throw new ImplementMePleaseException();
    }

    @Override
    @NonNull
    public Page<Station> findAll(@NonNull Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(database.values()), pageable, database.size());
    }

    @Override
    public <S extends Station> List<S> saveAll(Iterable<S> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public List<Station> findAll() {
        throw new ImplementMePleaseException();
    }

    @Override
    public List<Station> findAllById(Iterable<StationId> stationIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public long count() {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteById(StationId stationId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void delete(@NonNull Station entity) {
        database.remove(entity.stationQueryDto().stationId(), entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends StationId> stationIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAll(Iterable<? extends Station> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAll() {
        database.clear();
    }

    @Override
    public List<Station> findAll(Sort sort) {
        throw new ImplementMePleaseException();
    }
}