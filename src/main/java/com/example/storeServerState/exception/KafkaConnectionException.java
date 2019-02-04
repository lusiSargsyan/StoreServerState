package com.example.storeServerState.exception;

public class KafkaConnectionException extends Exception {
    public KafkaConnectionException(ErrorCodes code, String message) {
        super(code.getCode() + ":" + message);
    }

}
