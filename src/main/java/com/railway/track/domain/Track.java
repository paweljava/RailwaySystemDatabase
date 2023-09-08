package com.railway.track.domain;

import com.railway.station.domain.common.StationId;
import com.railway.track.domain.common.TrackId;
import com.railway.track.domain.dto.CreateTrackDto;
import com.railway.track.infrastructure.query.TrackQueryDto;
import com.railway.train.domain.common.TrainId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Builder
@Entity
@Table(name = "track")
@EqualsAndHashCode(of = "trackId")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
class Track {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "track_id", nullable = false))
    })
    private final TrackId trackId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "train_id", nullable = false))
    })
    private final TrainId trainId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "sourceStation_id", nullable = false))
    })
    private final StationId sourceStationId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "destinationStation_id", nullable = false))
    })
    private final StationId destinationStationId;

    @Column(name = "time_in", nullable = false)
    private final LocalTime timeIn;

    @Column(name = "time_out", nullable = false)
    private final LocalTime timeOut;

    public Track(TrackId trackId, TrainId trainId, StationId sourceStationId, StationId destinationStationId, LocalTime timeIn, LocalTime timeOut) {
        this.trackId = trackId;
        this.trainId = trainId;
        this.sourceStationId = sourceStationId;
        this.destinationStationId = destinationStationId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
    }

    final CreateTrackDto createTrackDto() {
        return CreateTrackDto.builder()
                .trainId(trainId)
                .sourceStationId(sourceStationId)
                .destinationStationId(destinationStationId)
                .timeIn(timeIn)
                .timeOut(timeOut)
                .build();
    }

    final TrackQueryDto trackQueryDto() {
        return TrackQueryDto.builder()
                .trackId(trackId)
                .trainId(trainId)
                .sourceStationId(sourceStationId)
                .destinationStationId(destinationStationId)
                .timeIn(timeIn)
                .timeOut(timeOut)
                .build();
    }
}
