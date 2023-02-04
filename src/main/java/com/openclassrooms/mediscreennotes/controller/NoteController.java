package com.openclassrooms.mediscreennotes.controller;

import com.openclassrooms.mediscreennotes.dto.NoteDTO;
import com.openclassrooms.mediscreennotes.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class NoteController {
    private NoteService noteService;

    @GetMapping("/notes/{patId}")
    public List<NoteDTO> getNotesPatient(@PathVariable int patId) {
        List<NoteDTO> allNotePatient = noteService.getAllNotePatient(patId);
        return allNotePatient;
    }

    @GetMapping("/notes")
    public List<NoteDTO> getNotes() {
        return noteService.getAllNote();
    }

    @GetMapping("/note/{id}")
    public NoteDTO getNote(@PathVariable String id) {
        return noteService.getNote(id);
    }

    @PostMapping("/note/add")
    public ResponseEntity<NoteDTO> addNote(@Valid @RequestBody NoteDTO noteDTO) {
        NoteDTO savedNoteDTO = noteService.addNote(noteDTO);
        return ResponseEntity.ok().body(savedNoteDTO);
    }

    @PutMapping("/note/update/{id}")
    public ResponseEntity<NoteDTO>updateNote(@PathVariable String id, @ Valid @RequestBody NoteDTO noteDTO) {
        noteDTO.setId(id);

        NoteDTO noteUpdate = noteService.update(id, noteDTO);

        return ResponseEntity.ok().body(noteUpdate);
    }

    @DeleteMapping("/note/delete/{id}")
    public void deleteNote(@PathVariable String id){
        noteService.deleteNote(id);
    }
}
