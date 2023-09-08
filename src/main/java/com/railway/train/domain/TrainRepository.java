package com.railway.train.domain;

import com.railway.train.domain.common.TrainId;
import com.railway.train.domain.exception.TrainNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

interface TrainRepository extends JpaRepository<Train, TrainId> {

    @NonNull
    <S extends Train> S save(@NonNull S train);

    @NonNull
    Optional<Train> findById(@NonNull TrainId trainId);

    @NonNull
    Page<Train> findAll(@NonNull Pageable pageable);

    @NonNull
    void delete(@NonNull Train train);

    void deleteAll();

    default Train findOneOrThrow(TrainId trainId) {
        return findById(trainId).orElseThrow(() -> new TrainNotFoundException(trainId));
    }
}