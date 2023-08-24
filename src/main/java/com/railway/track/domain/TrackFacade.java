package com.railway.track.domain;

import com.railway.station.domain.StationFacade;
import com.railway.station.domain.common.StationAddress;
import com.railway.station.domain.common.StationId;
import com.railway.station.domain.common.StationName;
import com.railway.station.domain.dto.CreateStationDto;
import com.railway.track.domain.common.TrackId;
import com.railway.track.domain.dto.CreateTrackDto;
import com.railway.track.infrastructure.query.TrackQueryDto;
import com.railway.train.domain.TrainFacade;
import com.railway.train.domain.common.TrainName;
import com.railway.train.domain.dto.CreateTrainDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class TrackFacade {

    private final TrackRepository trackRepository;
    private final TrackCreator trackCreator;
    private final TrackMapper mapper = new TrackMapper();
    @Autowired
    private StationFacade stationFacade;
    @Autowired
    private TrainFacade trainFacade;
List<Track> foundTracks = null;

    TrackFacade(TrackRepository trackRepository, TrackCreator trackCreator) {
        this.trackRepository = trackRepository;
        this.trackCreator = trackCreator;
    }

    public TrackQueryDto addTrack(CreateTrackDto createTrackDto) {
        requireNonNull(createTrackDto);
        final var track = trackCreator.createTrack(createTrackDto);
        trackRepository.save(track);
        return track.trackQueryDto();
    }

    public Page<TrackQueryDto> findAll(Pageable pageable) {
        requireNonNull(pageable);
        return trackRepository
                .findAll(pageable)
                .map(Track::trackQueryDto);
    }

    public List<TrackQueryDto> findTracks(StationId sourceStationId, StationId destinationStationId) {
        requireNonNull(sourceStationId);
        requireNonNull(destinationStationId);
        return mapper.map(trackRepository.findAll().stream()
                .filter(s -> s.getSourceStationId().equals(sourceStationId))
                .filter(d -> d.getDestinationStationId().equals(destinationStationId))
                .toList());
    }

    public TrackQueryDto findTrackById(TrackId trackId) {
        requireNonNull(trackId);
        final var track = trackRepository.getByIdOrThrow(trackId);
        return track.trackQueryDto();
    }

    public void delete(TrackId trackId) {
        requireNonNull(trackId);
        trackRepository.deleteById(trackId);
    }
}
