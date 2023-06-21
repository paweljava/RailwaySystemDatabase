package com.railway.domain.station;

import com.railway.domain.station.dto.StationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
class StationController {
    private final StationFacade stationFacade;

    private StationController(StationFacade stationFacade) {
        this.stationFacade = stationFacade;
    }

    @GetMapping("/stations")
    Page<StationDto> getStation(Pageable pageable) {
        return stationFacade.findAll(pageable);
    }

    @GetMapping("/station/{name}")
    StationDto getStation(@PathVariable String name) {
        return stationFacade.show(name);
    }

    @PostMapping("/station/add")
    StationDto createStation(@RequestBody StationDto stationDto) {
        return stationFacade.add(stationDto);
    }


    /*@GetMapping("/train{trainId}")
    public Optional<TrainSchedule> getTrainSchedule(@PathVariable UUID trainId) {
        return trainScheduleService.findById(trainId);
    }

    @PostMapping("/add/schedule")
    public TrainSchedule addSchedule(@RequestBody TrainScheduleDto trainScheduleDto) {
        return trainScheduleService.addSchedule(trainScheduleDto);
    }

    @GetMapping("/schedule")
    public List<TrainScheduleDto> getTrainSchedules() {
        return trainScheduleService.findAll();
    }

    @PostMapping("/add/train")
    public Train addTrain(@RequestBody TrainDto trainDto) {
        return trainService.addTrain(trainDto);
    }

    @PostMapping("/add/station")
    public Station addStation(@RequestBody StationDto stationDto) {
        return stationService.addStation(stationDto);
    }

    @GetMapping("/train/{id}")
    public Optional<Train> getTrain(@PathVariable("id") UUID id) {
        return trainService.getTrain(id);
    }

    *//*@GetMapping("/trains")
    public List<TrainDto> getTrains() {
        return trainService.findAll();
    }*//*

    @GetMapping("/trains")
    public List<Train> getTrains() {
        return trainService.findAll();
    }

    *//*@PostMapping("/add")
    public Station addStation(@RequestBody StationDto stationDto) {
        return stationService.addStation(stationDto);
    }*/

}
