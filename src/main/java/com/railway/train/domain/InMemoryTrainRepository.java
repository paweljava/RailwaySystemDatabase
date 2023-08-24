package com.railway.train.domain;

import com.railway.common.ImplementMePleaseException;
import com.railway.train.domain.common.TrainId;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

class InMemoryTrainRepository implements TrainRepository {

    private final ConcurrentHashMap<TrainId, Train> database = new ConcurrentHashMap<>();

    @Override
    @NonNull
    public <S extends Train> S save(@NonNull S train) {
        requireNonNull(train);
        database.putIfAbsent(train.trainQueryDto().trainId(), train);
        return train;
    }

    @Override
    @NonNull
    public Page<Train> findAll(@NonNull Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(database.values()), pageable, database.size());
    }

    @Override
    public void flush() {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> S saveAndFlush(S entity) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> List<S> saveAllAndFlush(Iterable<S> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllInBatch(Iterable<Train> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllByIdInBatch(Iterable<TrainId> trainIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAllInBatch() {
        throw new ImplementMePleaseException();
    }

    @Override
    public Train getOne(TrainId trainId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public Train getById(TrainId trainId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public Train getReferenceById(TrainId trainId) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> Optional<S> findOne(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> List<S> findAll(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> List<S> findAll(Example<S> example, Sort sort) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> long count(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> boolean exists(Example<S> example) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        throw new ImplementMePleaseException();
    }

    @Override
    public <S extends Train> List<S> saveAll(Iterable<S> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    @NonNull
    public Optional<Train> findById(@NonNull TrainId trainId) {
        return Optional.ofNullable(database.get(trainId));
    }

    @Override
    public boolean existsById(TrainId trainId) {
        throw new ImplementMePleaseException();
    }

    @Override
    @NonNull
    public List<Train> findAll() {
        throw new ImplementMePleaseException();
    }

    @Override
    public List<Train> findAllById(Iterable<TrainId> trainIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public long count() {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteById(@NonNull TrainId trainId) {
        database.remove(trainId);
    }

    @Override
    public void delete(@NonNull Train entity) {
        database.remove(entity.trainQueryDto().trainId(), entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends TrainId> trainIds) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAll(Iterable<? extends Train> entities) {
        throw new ImplementMePleaseException();
    }

    @Override
    public void deleteAll() {
        throw new ImplementMePleaseException();
    }

    @Override
    public List<Train> findAll(Sort sort) {
        throw new ImplementMePleaseException();
    }
}