package com.railway.domain.track;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TrackRepository extends MongoRepository<Track, String> {

}
