package com.railway.station.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = true)
public class StationConfiguration {

    StationFacade stationFacade() {
        return stationFacade(new InMemoryStationRepository());
    }

    @Bean
    StationFacade stationFacade(StationRepository stationRepository) {
        final var stationCreator = new StationCreator();
        return new StationFacade(stationRepository, stationCreator);
    }
}
