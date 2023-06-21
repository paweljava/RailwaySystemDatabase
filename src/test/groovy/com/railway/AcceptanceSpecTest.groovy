package com.railway

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AcceptanceSpecTest extends Specification {
    def "Positive flow integration test"() {
        given: 'inventory has stations name: Gdańsk, Warszawa, Kraków and tracks:' +
                'from Gdańsk at 6.00 hour to Warszawa at 8:00' +
                'from Warszawa at 8:10 to Kraków at 12:00' +
                'from Kraków at 12:10 to Warszawa at 16:00' +
                'from Warszawa at 16:10 to Gdańsk at 18:00'

        when: 'I go to /station'
        then: 'I see tree stations'

        when: 'I go to /train'
        then: 'I see trains'

        when: 'I go to /station/track'
        then: 'I see tree tracks from station'

        when: 'I go to /station1/track/station2'
        then: 'I see to tracks from station1 to station2'
    }
}