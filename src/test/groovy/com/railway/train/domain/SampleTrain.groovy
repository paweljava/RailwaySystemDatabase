package com.railway.train.domain

import com.railway.train.domain.common.TrainId
import com.railway.train.domain.common.TrainName
import com.railway.train.domain.dto.CreateTrainDto
import groovy.transform.CompileStatic

@CompileStatic
trait SampleTrain {

    CreateTrainDto inka = createTrainDto(TrainName.of("Inka"))
    CreateTrainDto awangarda = createTrainDto(TrainName.of("Awangarda"))
    CreateTrainDto stanczyk = createTrainDto(TrainName.of("Stańczyk"))

     private CreateTrainDto createTrainDto(TrainName name) {
        return CreateTrainDto.builder()
                .trainName(name)
                .build()
    }
}
