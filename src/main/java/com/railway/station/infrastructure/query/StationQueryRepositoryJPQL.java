package com.railway.station.infrastructure.query;


import com.railway.station.domain.common.StationId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StationQueryRepositoryJPQL {

    @Query("SELECT NEW com.railway.station.domain.Station(st.station_id, st.station_name, st.station_address) " +
            "FROM Station st JOIN Track trc ON st.station_id = trc.source_station_id WHERE trc.source_station_id = :stationId")
    StationQueryDto findById(@Param("stationId") StationId stationId);
}
