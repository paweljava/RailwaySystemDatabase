package com.railway.domain.station


import com.railway.domain.station.dto.StationNotFoundException
import com.railway.domain.station.dto.CreateStationDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import spock.lang.Specification

class StationFacadeUnitTest extends Specification implements SampleStations{

    private final StationFacade facade = new StationConfiguration().stationFacade()

    def "should get a station"() {
        when: "when add a station"
        facade.add(gdansk)

        then: "system has this station"
        facade.show(gdansk.name).getName() == "Gdańsk"
    }

    def "should throw exception when asked for a station that's not in the system"() {
        when: "system is asked for a station that is not present"
        facade.show("some station we don't have")

        then:
        thrown(StationNotFoundException)
    }

    def "should list stations"() {
        given: "we have stations in system"

        facade.add(gdansk)
        facade.add(warszawa)
        facade.add(krakow)

        when: "we ask for all stations"
        Page<CreateStationDto> foundStations = facade.findAll(new PageRequest(0, 10, Sort.unsorted()))

        then: "system returns the stations we have added"
        System.out.println("Found stations: " + foundStations.getContent())

        foundStations.getContent().stream().map(name -> name.getName() == gdansk.getName())
        foundStations.getContent().stream().map(name -> name.getName() == warszawa.getName())
        foundStations.getContent().stream().map(name -> name.getName() == krakow.getName())

        foundStations.getContent().get(0).getName().contains("Gdańsk")
        foundStations.getContent().get(1).getName().contains("Warszawa")
        foundStations.getContent().get(2).getName().contains("Kraków")
    }
}
