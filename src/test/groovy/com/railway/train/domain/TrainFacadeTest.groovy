package com.railway.train.domain

import com.railway.train.domain.common.TrainId
import com.railway.train.domain.exception.TrainNotFoundException
import com.railway.train.infrastructure.query.TrainQueryDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import spock.lang.Specification

class TrainFacadeTest extends Specification implements SampleTrain {

    private final TrainFacade trainFacade = new TrainConfiguration().trainFacade()

    def "should get a train"() {
        when: "when add a train"
        def trainAwangarda = trainFacade.addTrain(awangarda)

        then: "system has this train"
        def result = trainFacade.getTrain(trainAwangarda.trainId())
        result.trainId() == trainAwangarda.trainId()
        result.trainId().value == trainAwangarda.trainId().value
        result.trainId().getValue() == trainAwangarda.trainId().getValue()
        result.trainName().getValue() == trainAwangarda.trainName().getValue()
    }

    def "should throw exception when asked for a train that's not in the system"() {
        when: "system is asked for a train that is not present"
        trainFacade.getTrain(TrainId.of(UUID.randomUUID()))

        then:
        thrown(TrainNotFoundException)
    }

    def "should list trains"() {
        given: "we have trains in system"

        def trainInka = trainFacade.addTrain(inka)
        def trainAwangarda = trainFacade.addTrain(awangarda)
        def trainStanczyk = trainFacade.addTrain(stanczyk)

        when: "we ask for all trains"
        Page<TrainQueryDto> foundTrains = trainFacade.findAll(PageRequest.of(0, 10, Sort.unsorted()))

        then: "system returns the trains we have added"
        System.out.println("Found trains: " + foundTrains.getContent())

        foundTrains.stream().anyMatch({ it.trainId().getValue() == trainInka.trainId().getValue() })
        foundTrains.stream().anyMatch({ it.trainName().getValue() == trainInka.trainName().getValue() })
                                           
        foundTrains.stream().anyMatch({ it.trainId().getValue() == trainAwangarda.trainId().getValue() })
        foundTrains.stream().anyMatch({ it.trainName().getValue() == trainAwangarda.trainName().getValue() })
                                           
        foundTrains.stream().anyMatch({ it.trainId().getValue() == trainStanczyk.trainId().getValue() })
        foundTrains.stream().anyMatch({ it.trainName().getValue() == trainStanczyk.trainName().getValue() })
    }
}
