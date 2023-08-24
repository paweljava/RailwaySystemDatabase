package com.railway.train.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TrainConfiguration {

    TrainFacade trainFacade() {
        return trainFacade(new InMemoryTrainRepository());
    }

    @Bean
    TrainFacade trainFacade(TrainRepository trainRepository) {
        final var trainCreator = new TrainCreator();
        return new TrainFacade(trainRepository, trainCreator);
    }
}
