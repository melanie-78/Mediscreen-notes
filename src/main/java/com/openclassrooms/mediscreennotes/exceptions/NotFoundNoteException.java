package com.openclassrooms.mediscreennotes.exceptions;

public class NotFoundNoteException extends RuntimeException {
    public NotFoundNoteException(String message) {
        super(message);
    }
}
