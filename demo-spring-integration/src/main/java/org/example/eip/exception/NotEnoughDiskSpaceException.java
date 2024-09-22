package org.example.eip.exception;

public class NotEnoughDiskSpaceException extends Exception{
    public NotEnoughDiskSpaceException(String message) {
        super(message);
    }
}
