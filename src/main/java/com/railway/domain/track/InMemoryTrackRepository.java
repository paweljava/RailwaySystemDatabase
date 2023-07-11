package com.railway.domain.track;

import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

class InMemoryTrackRepository implements TrackRepository {

    private final HashMap<String, Track> map = new HashMap<>();

    @Override
    public Track save(Track track) {
        requireNonNull(track);
        map.put(track.createDto().getTrainId(), track);
        return track;
    }

    @Override
    public Page<Track> findAll(Pageable pageable) {
        return new PageImpl<>(new ArrayList<>(map.values()), pageable, map.size());
    }

    @Override
    public <S extends Track> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Track> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Track> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Track> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Track> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Track> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Track> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Track> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Track, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Track> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Track> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Track> findAll() {
        return null;
    }

    @Override
    public List<Track> findAllById(Iterable<String> strings) {
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
    public void delete(Track entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Track> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Track> findAll(Sort sort) {
        return null;
    }
}
