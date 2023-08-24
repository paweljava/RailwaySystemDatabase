package com.railway.train.domain.common;

import lombok.*;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class TrainName implements Serializable {

    private final String value;
}
