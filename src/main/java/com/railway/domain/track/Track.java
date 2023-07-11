package com.railway.domain.track;

import com.railway.domain.track.dto.CreateTrackDto;
import com.railway.domain.track.dto.TrackQueryDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Getter
@Builder
@Document(collection = "track")
class Track {

    @Id
    private final String id;
    private final String trainId;
    private final String sourceStationId;
    private final String destinationStationId;
    private final LocalTime timeIn;
    private final LocalTime timeOut;

    public Track(LocalTime timeIn, LocalTime timeOut) {
        this.id = UUID.randomUUID().toString();
        this.trainId = this.getTrainId();
        this.sourceStationId = this.getTrainId();
        this.destinationStationId = this.getSourceStationId();
        this.timeIn = LocalTime.parse(timeIn.plus(2, ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern("HH:mm")));
        this.timeOut = LocalTime.parse(timeOut.plus(2, ChronoUnit.HOURS).format(DateTimeFormatter.ofPattern("HH:mm")));
    }

    final CreateTrackDto createDto() {
        return CreateTrackDto.builder()
                .trainId(trainId)
                .sourceStationId(sourceStationId)
                .destinationStationId(destinationStationId)
                .timeIn(timeIn)
                .timeOut(timeOut)
                .build();
    }

    final TrackQueryDto queryDto() {
        return TrackQueryDto.builder()
                .sourceStationId(sourceStationId)
                .destinationStationId(destinationStationId)
                .timeIn(timeIn)
                .timeOut(timeOut)
                .build();
    }
}
