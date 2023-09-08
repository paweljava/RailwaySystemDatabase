package com.railway.track.infrastructure;

import com.railway.station.domain.common.StationId;
import com.railway.track.domain.application.*;
import com.railway.track.domain.common.TrackId;
import com.railway.track.domain.dto.CreateTrackDto;
import com.railway.track.infrastructure.query.TrackQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
class TrackController {

    private final TrackAdd trackAdd;
    private final TrackFindAll trackFindAll;
    private final TrackFindByTrackId trackFindByTrackId;
    private final TrackFindByStartAndEndStationId trackFindByStartAndEndStationId;
    private final TrackRemove trackRemove;

    TrackController(TrackAdd trackAdd, TrackFindAll trackFindAll, TrackFindByTrackId trackFindByTrackId, TrackFindByStartAndEndStationId trackFindByStartAndEndStationId, TrackRemove trackRemove) {
        this.trackAdd = trackAdd;
        this.trackFindAll = trackFindAll;
        this.trackFindByTrackId = trackFindByTrackId;
        this.trackFindByStartAndEndStationId = trackFindByStartAndEndStationId;
        this.trackRemove = trackRemove;
    }

    @GetMapping("/track")
    TrackQueryDto getTrack(@RequestParam UUID id) {
        return trackFindByTrackId.findTrackById(TrackId.of(id));
    }

    @GetMapping("/all-tracks")
    Page<TrackQueryDto> getTracks(Pageable pageable) {
        return trackFindAll.findAll(pageable);
    }


    @GetMapping("/tracks")
    List<TrackQueryDto> getTracksByStations(@RequestParam UUID startStationId, @RequestParam UUID endStationId) {
        return trackFindByStartAndEndStationId.findTracks(StationId.of(startStationId), StationId.of(endStationId));
    }

    @PostMapping("/track/add")
    TrackQueryDto createTrack(@RequestBody CreateTrackDto createTrackDto) {
        return trackAdd.addTrack(createTrackDto);
    }

    @DeleteMapping("track/delete")
    void getTracks(@RequestParam("trackId") TrackId trackId) {
        trackRemove.delete(trackId);
    }
}