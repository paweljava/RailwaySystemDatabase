package com.railway.domain.train;

import com.railway.domain.train.dto.TrainDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static java.util.Objects.requireNonNull;

public class TrainFacade {
    private final TrainRepository trainRepository;
    private final TrainCreator trainCreator;

    TrainFacade(TrainRepository trainRepository, TrainCreator trainCreator) {
        this.trainRepository = trainRepository;
        this.trainCreator = trainCreator;
    }

    public TrainDto add(TrainDto trainDto) {
        requireNonNull(trainDto);
        final var train = trainCreator.from(trainDto);
        trainRepository.save(train);
        return train.dto();
    }

    public TrainDto show(String name) {
        requireNonNull(name);
        final var train = trainRepository.findOneOrThrow(name);
        return train.dto();
    }

    public Page<TrainDto> findAll(Pageable pageable) {
        requireNonNull(pageable);
        return trainRepository
                .findAll(pageable)
                .map(Train::dto);
    }

    /*public void delete(String name) {
        requireNonNull(name);
        trainRepository.delete(name);
    }*/
}
