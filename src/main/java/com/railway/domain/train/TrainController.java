package com.railway.domain.train;

import com.railway.domain.train.dto.TrainDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class TrainController {
    private final TrainFacade trainFacade;

    private TrainController(TrainFacade trainFacade) {
        this.trainFacade = trainFacade;
    }

    @GetMapping("/trains")
    Page<TrainDto> getTrains(Pageable pageable) {
        return trainFacade.findAll(pageable);
    }

    @GetMapping("/train/{name}")
    TrainDto getTrain(@PathVariable String name) {
        return trainFacade.show(name);
    }

    @PostMapping("/train/add")
    TrainDto createTrain(@RequestBody TrainDto trainDto) {
        return trainFacade.add(trainDto);
    }
}
