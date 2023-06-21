package com.railway.domain.train

import com.railway.domain.train.dto.TrainDto
import com.railway.domain.train.dto.TrainNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import spock.lang.Specification

class TrainSpec extends Specification implements SampleTrain {

    private final TrainFacade trainFacade = new TrainConfiguration().trainFacade()

    def "should get a train"() {
        when: "when add a train"
        trainFacade.add(awangarda)

        then: "system has this train"
        trainFacade.show(awangarda.name).getName() == "Awangarda"
    }

    def "should throw exception when asked for a train that's not in the system"() {
        when: "system is asked for a train that is not present"
        trainFacade.show("some train we don't have")

        then:
        thrown(TrainNotFoundException)
    }

    // pytanie - dlaczego inka zostala dodana na trzecim miejscu ?
    def "should list trains"() {
        given: "we have trains in system"

        trainFacade.add(inka)
        trainFacade.add(awangarda)
        trainFacade.add(stanczyk)

        when: "we ask for all trains"
        Page<TrainDto> foundTrains = trainFacade.findAll(new PageRequest(0, 10, Sort.unsorted()))

        then: "system returns the trains we have added"
        System.out.println("Found trains: " + foundTrains.getContent())

        foundTrains.getContent().stream().map(name -> name.getName() == inka.getName())
        foundTrains.getContent().stream().map(name -> name.getName() == awangarda.getName())
        foundTrains.getContent().stream().map(name -> name.getName() == stanczyk.getName())

        foundTrains.getContent().get(0).getName().contains(stanczyk.getName())
        foundTrains.getContent().get(1).getName().contains(awangarda.getName())
        foundTrains.getContent().get(2).getName().contains(inka.getName())
    }
}
