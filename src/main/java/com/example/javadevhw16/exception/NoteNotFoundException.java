package com.example.javadevhw16.exception;

import java.util.UUID;

public class NoteNotFoundException extends RuntimeException{
    public NoteNotFoundException(UUID noteId) {
        super("Note with id " + noteId + " not found");
    }
}
