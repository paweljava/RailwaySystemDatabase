CREATE TABLE IF NOT EXISTS train
(
    train_id UUID NOT NULL,
    train_name VARCHAR(50) NOT NULL,
    CONSTRAINT train_pkey PRIMARY KEY (train_id)
);

CREATE TABLE IF NOT EXISTS station
(
    station_id UUID NOT NULL,
    station_name VARCHAR(50) NOT NULL,
    station_address VARCHAR(100) NOT NULL,
    CONSTRAINT station_pkey PRIMARY KEY (station_id)
);

CREATE TABLE IF NOT EXISTS track
(
    track_id UUID NOT NULL,
    train_id UUID NOT NULL,
    source_station_id UUID NOT NULL,
    destination_station_id UUID NOT NULL,
    time_in TIME,
    time_out TIME,
    CONSTRAINT track_pkey PRIMARY KEY (track_id),
    FOREIGN KEY (train_id) REFERENCES train(train_id),
    FOREIGN KEY (source_station_id) REFERENCES station (station_id),
    FOREIGN KEY (destination_station_id) REFERENCES station (station_id)
);



