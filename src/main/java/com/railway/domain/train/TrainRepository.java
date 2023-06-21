package com.railway.domain.train;

import com.railway.domain.train.dto.TrainNotFoundException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TrainRepository extends MongoRepository<Train, String> {
    Train save(Train station);

    Train findByName(@NonNull String name);

    Page<Train> findAll(Pageable pageable);

    default Train findOneOrThrow(String name) {
        final var train = findByName(name);
        if (train == null) {
            throw new TrainNotFoundException(name);
        }
        return train;
    }

    /*void delete(String name);*/
}