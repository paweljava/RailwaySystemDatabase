package com.railway;

/*import com.railway.domain.station.StationRepository;
import com.railway.repository.TrackRepository;
import com.railway.repository.TrainRepository;
import com.railway.repository.TrainScheduleRepository;*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableAutoConfiguration
//@EnableMongoRepositories(basePackageClasses = {StationRepository.class, TrackRepository.class, TrainRepository.class, TrainScheduleRepository.class})
//@EnableAspectJAutoProxy
public class RailwaySystemApplication {
    public static void main(String[] args) {
            SpringApplication.run(RailwaySystemApplication.class, args);
    }
}