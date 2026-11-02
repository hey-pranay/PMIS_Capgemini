package com.university.placement.exception;

/** The requested resource already exists. */
public class DuplicateResourceException extends PlacementException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
