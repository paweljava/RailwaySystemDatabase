package com.railway.domain.station;

import com.railway.domain.station.dto.StationDto;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "station")
class Station {

    @Id
    private final String id;
    private final String name;
    private final String address;

    final StationDto dto() {
        return StationDto.builder()
                .name(name)
                .address(address)
                .build();
    }
}
