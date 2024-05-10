package com.example.javadevhw16.controller;

import com.example.javadevhw16.data.entity.Note;
import com.example.javadevhw16.exception.NoteNotFoundException;
import com.example.javadevhw16.services.NoteService;
import jakarta.persistence.EntityNotFoundException;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/list")
    public ResponseEntity<List<Note>> getList() {
        List<Note> notes = noteService.listAll();
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/edit/{id}")
    public ResponseEntity<Note> getNoteForEdit(@PathVariable UUID id) {
        try {
            Note note = noteService.getById(id);
            return ResponseEntity.ok(note);
        } catch (NoteNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editNote(
            @PathVariable UUID id,
            @RequestBody @NotNull Note updatedNote) {
        try {
            noteService.update(updatedNote);
            return ResponseEntity.ok("Note updated successfully");
        } catch (NoteNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteNoteById(@PathVariable UUID id) {
        try {
            noteService.deleteById(id);
            return ResponseEntity.ok("Note deleted successfully");
        } catch (NoteNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNote(@RequestBody Note newNote) {
        noteService.add(newNote);
        return ResponseEntity.ok("Note created successfully");
    }
}
