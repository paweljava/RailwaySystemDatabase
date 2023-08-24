package com.railway.train.domain;

import com.railway.train.domain.common.TrainId;
import com.railway.train.domain.common.TrainName;
import com.railway.train.infrastructure.query.TrainQueryDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(of = "trainId")
@Table(name = "train")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)class Train {

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "train_id", nullable = false))
    })
    private final TrainId trainId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "train_name", nullable = false))
    })
    private final TrainName trainName;

    Train(TrainId trainId, TrainName trainName) {
        this.trainId = trainId;
        this.trainName = trainName;
    }

    final TrainQueryDto trainQueryDto() {
        return TrainQueryDto.builder()
                .trainId(trainId)
                .trainName(trainName)
                .build();
    }
}

