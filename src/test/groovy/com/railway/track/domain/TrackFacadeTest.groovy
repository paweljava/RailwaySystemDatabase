package com.railway.track.domain

import com.railway.track.domain.common.TrackId
import com.railway.track.domain.exception.TrackNotFoundException
import com.railway.track.infrastructure.query.TrackQueryDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import spock.lang.Specification

class TrackFacadeTest extends Specification implements SampleTracks {
    def cleanup() {
    }

    private final TrackFacade trackFacade = new TrackConfiguration().trackFacade()

    def "should get a track"() {
        when: "when add a track"

        def trackKrakowWarszawa = trackFacade.addTrack(krakowWarszawa)


        then: "system has this track"
        def result = trackFacade.findTrackById(trackKrakowWarszawa.trackId())

        result.trackId() == trackKrakowWarszawa.trackId()
        result.trackId().getValue() == trackKrakowWarszawa.trackId().getValue()
        result.trainId() == trackKrakowWarszawa.trainId()
        result.trainId().value == trackKrakowWarszawa.trainId().value
        result.sourceStationId() == trackKrakowWarszawa.sourceStationId()
        result.sourceStationId().getValue() == trackKrakowWarszawa.sourceStationId().value
        result.destinationStationId().getValue() == trackKrakowWarszawa.destinationStationId().value
        result.timeIn() == trackKrakowWarszawa.timeIn()
        result.timeOut() == trackKrakowWarszawa.timeOut()

    }

    def "should get a tracks by source station id and destination station id"() {
        given: "we have tracks in system"
        def trackGdanskWarszawa = trackFacade.addTrack(gdanskWarszawa)
        def trackWarszawaKrakow = trackFacade.addTrack(warszawaKrakow)
        def trackKrakowWarszawa = trackFacade.addTrack(krakowWarszawa)
        def trackWarszawaGdansk = trackFacade.addTrack(warszawaGdansk)

        when: "we ask for track by station id"
        def result = trackFacade.findTracks(trackGdanskWarszawa.sourceStationId(),
                trackGdanskWarszawa.destinationStationId())

        then: "system has this track"
        result.stream().anyMatch { it.trainId().getValue() == trackGdanskWarszawa.trainId().getValue() }
        result.stream().anyMatch { it.sourceStationId().getValue() == trackGdanskWarszawa.sourceStationId().getValue() }
        result.stream().anyMatch { it.destinationStationId().getValue() == trackGdanskWarszawa.destinationStationId().getValue() }
        result.stream().anyMatch { it.timeIn() == trackGdanskWarszawa.timeIn() }
        result.stream().anyMatch { it.timeOut() == trackGdanskWarszawa.timeOut() }
    }

    def "should throw exception when asked for a track that's not in the system"() {
        when: "system is asked for a track that is not present"
        trackFacade.findTrackById(TrackId.of(UUID.randomUUID()))

        then: "system threw an exception"
        thrown(TrackNotFoundException)
    }

    def "should list tracks"() {
        given: "we have tracks in system Gdańsk-Warszawa-Kraków, Kraków-Warszawa-Gdańsk"
        def trackGdanskWarszawa = trackFacade.addTrack(gdanskWarszawa)
        def trackWarszawaKrakow = trackFacade.addTrack(warszawaKrakow)
        def trackKrakowWarszawa = trackFacade.addTrack(krakowWarszawa)
        def trackWarszawaGdansk = trackFacade.addTrack(warszawaGdansk)

        when: "we ask for all tracks"
        Page<TrackQueryDto> foundTracks = trackFacade.findAll(PageRequest.of(0, 10, Sort.unsorted()))

        then: "system returns the tracks we have added"
        foundTracks.stream().anyMatch { it.trackId().getValue() == trackGdanskWarszawa.trackId().getValue() }
        foundTracks.stream().anyMatch { it.trainId().getValue() == trackGdanskWarszawa.trainId().getValue() }
        foundTracks.stream().anyMatch { it.sourceStationId().getValue() == trackGdanskWarszawa.sourceStationId().getValue() }
        foundTracks.stream().anyMatch { it.destinationStationId().getValue() == trackGdanskWarszawa.destinationStationId().getValue() }
        foundTracks.stream().anyMatch { it.timeIn() == trackGdanskWarszawa.timeIn() }
        foundTracks.stream().anyMatch { it.timeOut() == trackGdanskWarszawa.timeOut() }

        foundTracks.stream().anyMatch { it.trackId().getValue() == trackWarszawaKrakow.trackId().getValue() }
        foundTracks.stream().anyMatch { it.trainId().getValue() == trackWarszawaKrakow.trainId().getValue() }
        foundTracks.stream().anyMatch { it.sourceStationId().getValue() == trackWarszawaKrakow.sourceStationId().getValue() }
        foundTracks.stream().anyMatch { it.destinationStationId().getValue() == trackWarszawaKrakow.destinationStationId().getValue() }
        foundTracks.stream().anyMatch { it.timeIn() == trackWarszawaKrakow.timeIn() }
        foundTracks.stream().anyMatch { it.timeOut() == trackWarszawaKrakow.timeOut() }

        foundTracks.stream().anyMatch { it.trackId().getValue() == trackKrakowWarszawa.trackId().getValue() }
        foundTracks.stream().anyMatch { it.trainId().getValue() == trackKrakowWarszawa.trainId().getValue() }
        foundTracks.stream().anyMatch { it.sourceStationId().getValue() == trackKrakowWarszawa.sourceStationId().getValue() }
        foundTracks.stream().anyMatch { it.destinationStationId().getValue() == trackKrakowWarszawa.destinationStationId().getValue() }
        foundTracks.stream().anyMatch { it.timeIn() == trackKrakowWarszawa.timeIn() }
        foundTracks.stream().anyMatch { it.timeOut() == trackKrakowWarszawa.timeOut() }

        foundTracks.stream().anyMatch { it.trackId().getValue() == trackWarszawaGdansk.trackId().getValue() }
        foundTracks.stream().anyMatch { it.trainId().getValue() == trackWarszawaGdansk.trainId().getValue() }
        foundTracks.stream().anyMatch { it.sourceStationId().getValue() == trackWarszawaGdansk.sourceStationId().getValue() }
        foundTracks.stream().anyMatch { it.destinationStationId().getValue() == trackWarszawaGdansk.destinationStationId().getValue() }
        foundTracks.stream().anyMatch { it.timeIn() == trackWarszawaGdansk.timeIn() }
        foundTracks.stream().anyMatch { it.timeOut() == trackWarszawaGdansk.timeOut() }
    }
}

