package com.railway.track.infrastructure;

import com.railway.station.domain.common.StationId;
import com.railway.track.domain.TrackFacade;
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

    private final TrackFacade trackFacade;

    private TrackController(TrackFacade trackFacade) {
        this.trackFacade = trackFacade;
    }

    @GetMapping("/track")
    TrackQueryDto getTrack(@RequestParam UUID id) {
        return trackFacade.findTrackById(TrackId.of(id));
    }

    @GetMapping("/tracks")
    Page<TrackQueryDto> getTracks(Pageable pageable) {
        return trackFacade.findAll(pageable);
    }


    @GetMapping("/tracksbystationsids")
    List<TrackQueryDto> getTracksByIdsStations(@RequestParam UUID startStationId, @RequestParam UUID endStationId) {
        return trackFacade.findTracks(StationId.of(startStationId), StationId.of(endStationId));
    }

    @PostMapping("/track/add")
    TrackQueryDto createTrack(@RequestBody CreateTrackDto createTrackDto) {
        return trackFacade.addTrack(createTrackDto);
    }

    @DeleteMapping("track/delete")
    void getTracks(@RequestParam ("trackId") TrackId trackId) {
        trackFacade.delete(trackId);
    }
}