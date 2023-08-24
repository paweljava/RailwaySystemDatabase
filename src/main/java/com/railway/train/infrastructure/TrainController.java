package com.railway.train.infrastructure;

import com.railway.train.application.TrainAdd;
import com.railway.train.application.TrainFindAll;
import com.railway.train.application.TrainFindByTrainId;
import com.railway.train.application.TrainRemove;
import com.railway.train.domain.common.TrainId;
import com.railway.train.domain.dto.CreateTrainDto;
import com.railway.train.infrastructure.query.TrainQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
class TrainController {

    private final TrainAdd trainAdd;
    private final TrainFindAll trainFindAll;
    private final TrainFindByTrainId trainFindByTrainId;
    private final TrainRemove trainRemove;

    TrainController(TrainAdd trainAdd, TrainFindAll trainFindAll, TrainFindByTrainId trainFindByTrainId, TrainRemove trainRemove) {
        this.trainAdd = trainAdd;
        this.trainFindAll = trainFindAll;
        this.trainFindByTrainId = trainFindByTrainId;
        this.trainRemove = trainRemove;
    }

    @GetMapping("/trains")
    Page<TrainQueryDto> getTrains(Pageable pageable) {
        return trainFindAll.findAll(pageable);
    }

    @GetMapping("/train/{id}")
    TrainQueryDto getTrain(@PathVariable UUID id) {
        return trainFindByTrainId.getTrain(TrainId.of(id));
    }

    @PostMapping("/train/add")
    TrainQueryDto addTrain(@RequestBody CreateTrainDto createTrainDto) {
        return trainAdd.addTrain(createTrainDto);
    }

    @DeleteMapping("/train/{id}")
    void deleteStation(@PathVariable UUID id) {
        trainRemove.deleteTrain(TrainId.of(id));
    }
}
