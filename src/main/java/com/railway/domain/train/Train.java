package com.railway.domain.train;

import com.railway.domain.train.dto.TrainDto;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "train")
class Train {

    @Id
    private final String id;
    private final String name;
    private final String address;

    final TrainDto dto() {
        return TrainDto.builder()
                .name(name)
                .build();
    }
}

