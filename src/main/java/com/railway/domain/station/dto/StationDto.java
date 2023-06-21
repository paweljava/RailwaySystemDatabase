package com.railway.domain.station.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StationDto {

    private final String name;
    private final String address;
}
