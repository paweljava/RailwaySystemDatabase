package com.railway.domain.track;

import com.railway.domain.track.dto.CreateTrackDto;
import com.railway.domain.track.dto.TrackQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.railway.domain.track.TrackMapper.mapper;
import static java.util.Objects.requireNonNull;

public class TrackFacade {

    private final TrackRepository trackRepository;
    private final TrackCreator trackCreator;

    TrackFacade(TrackRepository trackRepository, TrackCreator trackCreator) {
        this.trackRepository = trackRepository;
        this.trackCreator = trackCreator;
    }

    public CreateTrackDto addTrack(CreateTrackDto createTrackDto) {
        requireNonNull(createTrackDto);
        final var track = trackCreator.createTrack(createTrackDto);
        trackRepository.save(track);
        return track.createDto();
    }

    public Page<TrackQueryDto> findAll(Pageable pageable) {
        requireNonNull(pageable);
        return trackRepository
                .findAll(pageable)
                .map(Track::queryDto);
    }

    public List<TrackQueryDto> findTracks(String sourceStationId, String destinationStationId) {
        requireNonNull(sourceStationId, destinationStationId);
        final var sourceStation = trackRepository.findAllById(Collections.singleton(sourceStationId));
        return mapper(sourceStation.stream()
                .filter(city -> city.getDestinationStationId()
                        .contains(destinationStationId))
                .toList());
    }
}
