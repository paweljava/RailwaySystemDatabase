package com.railway.domain.train

import com.railway.domain.station.dto.StationDto
import com.railway.domain.train.dto.TrainDto
import groovy.transform.CompileStatic

@CompileStatic
trait SampleTrain {

    TrainDto inka = createTrainDto("Inka")
    TrainDto awangarda = createTrainDto("Awangarda")
    TrainDto stanczyk = createTrainDto("Stańczyk")

     private TrainDto createTrainDto(String name) {
        return TrainDto.builder()
                .name(name)
                .build()
    }
}
