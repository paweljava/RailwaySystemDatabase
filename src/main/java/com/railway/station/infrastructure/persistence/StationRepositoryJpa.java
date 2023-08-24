/*
package com.railway.station.infrastructure.persistence;

import com.railway.station.domain.Station;
import com.railway.station.domain.StationRepository;
import com.railway.station.domain.common.StationId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface StationRepositoryJpa implements JpaRepository<Station, StationId> {
    @Override
    public Station save(Station station) {
        return null;
    }

    @Override
    public Page<Station> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Station> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Station> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Station> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<StationId> stationIds) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Station getOne(StationId stationId) {
        return null;
    }

    @Override
    public Station getById(StationId stationId) {
        return null;
    }

    @Override
    public Station getReferenceById(StationId stationId) {
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
    public Optional<Station> findById(StationId stationId) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(StationId stationId) {
        return false;
    }

    @Override
    public List<Station> findAll() {
        return null;
    }

    @Override
    public List<Station> findAllById(Iterable<StationId> stationIds) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(StationId stationId) {

    }

    @Override
    public void delete(Station entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends StationId> stationIds) {

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
*/
