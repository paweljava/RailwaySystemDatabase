package com.railway.domain.station;

import lombok.NonNull;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

class InMemoryStationRepository implements StationRepository {

    private final HashMap<String, Station> map = new HashMap<>();

    @Override
    public Station save(Station station) {
        requireNonNull(station);
        map.put(station.dto().getName(), station);
        return station;
    }

    @Override
    public Station findByName(@NonNull String name) {
        return map.get(name);
    }

    @Override
    public Page<Station> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public <S extends Station> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Station> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Station> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Station> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Station> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Station> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Station> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Station> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Station, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Station> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Station> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Station> findAll() {
        return null;
    }

    @Override
    public List<Station> findAllById(Iterable<String> strings) {
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
    public void delete(Station entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Station> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Station> findAll(Sort sort) {
        return null;
    }
}
