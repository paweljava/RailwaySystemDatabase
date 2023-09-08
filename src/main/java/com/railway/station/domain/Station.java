package com.railway.station.domain;

import com.railway.station.domain.common.StationAddress;
import com.railway.station.domain.common.StationId;
import com.railway.station.domain.common.StationName;
import com.railway.station.domain.dto.CreateStationDto;
import com.railway.station.infrastructure.query.StationQueryDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(of = "stationId")
@Table(name = "station")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
class Station {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "station_id", insertable = false, updatable = false, nullable = false))
    })
    private final StationId stationId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "station_name", nullable = false))
    })
    private final StationName stationName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "station_address", nullable = false))
    })
    private final StationAddress stationAddress;

    Station(StationId stationId, StationName stationName, StationAddress stationAddress) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.stationAddress = stationAddress;
    }

    final CreateStationDto createStationDto() {
        return CreateStationDto.builder()
                .stationName(stationName)
                .stationAddress(stationAddress)
                .build();
    }

    final StationQueryDto stationQueryDto() {
        return StationQueryDto.builder()
                .stationId(stationId)
                .stationName(stationName)
                .stationAddress(stationAddress)
                .build();
    }
}