package com.railway.domain.train.dto;

public class TrainNotFoundException extends RuntimeException{

    public TrainNotFoundException(String id) {
        super("No train of id " + id + " found");
    }

}
