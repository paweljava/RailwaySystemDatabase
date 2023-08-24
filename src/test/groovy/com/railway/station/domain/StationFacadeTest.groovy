package com.railway.station.domain

import com.railway.station.domain.common.StationId
import com.railway.station.domain.exception.StationNotFoundException
import com.railway.station.infrastructure.query.StationQueryDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import spock.lang.Specification

class StationFacadeTest extends Specification implements SampleStations {

    private final StationFacade facade = new StationConfiguration().stationFacade()

    def "should get a station"() {
        when: "when add a station"
            def stationGdansk = facade.addStation(gdansk)

        then: "system has this station"
            def result = facade.getStation(stationGdansk.stationId())

            result.stationId() == stationGdansk.stationId()
            result.stationId().value == stationGdansk.stationId().value
            result.stationId().getValue() == stationGdansk.stationId().getValue()
            result.stationName().getValue() == stationGdansk.stationName().getValue()
            result.stationAddress().getValue() == stationGdansk.stationAddress().getValue()
    }

    def "should throw exception when asked for a station that's not in the system"() {
        when: "system is asked for a station that is not present"
            facade.getStation(StationId.of(UUID.randomUUID()))

        then: "system threw an exception"
            thrown(StationNotFoundException)
    }

    def "should list stations"() {
        given: "we have stations in system Gdańsk, Warszawa i Kraków"
             def stationGdansk = facade.addStation(gdansk)
             def stationWarszawa = facade.addStation(warszawa)
             def stationKrakow = facade.addStation(krakow)

        when: "we ask for all stations"
            Page<StationQueryDto> foundStations = facade.findAll(PageRequest.of(0, 10, Sort.unsorted()))

        then: "system returns the stations we have added"
            foundStations.stream().anyMatch { it.stationId().getValue() == stationGdansk.stationId().getValue() }
            foundStations.stream().anyMatch { it.stationName().getValue() == stationGdansk.stationName().getValue() }
            foundStations.stream().anyMatch { it.stationAddress().getValue() == stationGdansk.stationAddress().getValue() }

            foundStations.stream().anyMatch { it.stationId().getValue() == stationWarszawa.stationId().getValue() }
            foundStations.stream().anyMatch { it.stationName().getValue() == stationWarszawa.stationName().getValue() }
            foundStations.stream().anyMatch { it.stationAddress().getValue() == stationWarszawa.stationAddress().getValue() }

            foundStations.stream().anyMatch { it.stationId().getValue() == stationKrakow.stationId().getValue() }
            foundStations.stream().anyMatch { it.stationName().getValue() == stationKrakow.stationName().getValue() }
            foundStations.stream().anyMatch { it.stationAddress().getValue() == stationKrakow.stationAddress().getValue() }
    }
}
