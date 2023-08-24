package com.railway.track.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
class TrackConfiguration {

    TrackFacade trackFacade() {
        return trackFacade(new InMemoryTrackRepository());
    }

    @Bean
    TrackFacade trackFacade(TrackRepository trackRepository) {
        final var trackCreator = new TrackCreator();
        return new TrackFacade(trackRepository, trackCreator);
    }
}
