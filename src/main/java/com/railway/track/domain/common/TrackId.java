package com.railway.track.domain.common;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class TrackId implements Serializable {

    private final UUID value;
}