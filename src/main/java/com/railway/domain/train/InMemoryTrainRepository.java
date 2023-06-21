package com.railway.domain.train;

import lombok.NonNull;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

class InMemoryTrainRepository implements TrainRepository {

    private final HashMap<String, Train> map = new HashMap<>();

    @Override
    public Train save(Train train) {
        requireNonNull(train);
        map.put(train.dto().getName(), train);
        return train;
    }

    @Override
    public Train findByName(@NonNull String name) {
        return map.get(name);
    }

    @Override
    public Page<Train> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public <S extends Train> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Train> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Train> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Train> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Train> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Train> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Train> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Train> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Train, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Train> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Train> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Train> findAll() {
        return null;
    }

    @Override
    public List<Train> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Train entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Train> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Train> findAll(Sort sort) {
        return null;
    }
}