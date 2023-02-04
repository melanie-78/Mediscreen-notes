package com.openclassrooms.mediscreennotes.service;

import com.openclassrooms.mediscreennotes.dto.NoteDTO;
import com.openclassrooms.mediscreennotes.exceptions.NotFoundNoteException;
import com.openclassrooms.mediscreennotes.mapper.NoteMapper;
import com.openclassrooms.mediscreennotes.model.Note;
import com.openclassrooms.mediscreennotes.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService{
    private NoteRepository noteRepository;
    private NoteMapper dtoNote;

    @Override
    public NoteDTO addNote(NoteDTO noteDTO) {
        Note note = dtoNote.fromNoteDTO(noteDTO);
        Note insert = noteRepository.insert(note);

        return  dtoNote.fromNote(insert);
    }


    @Override
    public NoteDTO getNote(String id) {
        Note noteById = noteRepository.findById(id)
                        .orElseThrow(()->new NotFoundNoteException("This note doesn't exist in database"));
        return dtoNote.fromNote(noteById);
    }

    @Override
    public List<NoteDTO> getAllNote() {
        List<Note> all = noteRepository.findAll();
        List<NoteDTO> allNotes = all.stream().map(note -> {
            NoteDTO noteDTO = dtoNote.fromNote(note);
            return noteDTO;
        }).collect(Collectors.toList());
        return allNotes;
    }

    @Override
    public void deleteNote(String id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundNoteException("This note doesn't exist in database"));
        noteRepository.delete(note);
    }

    @Override
    public List<NoteDTO> getAllNotePatient(int patId) {
        List<Note> notes = noteRepository.findByPatId(patId);
        List<NoteDTO> noteDTOs = notes.stream().map(note -> {
            NoteDTO noteDTO = dtoNote.fromNote(note);
            return noteDTO;
        }).collect(Collectors.toList());

        return noteDTOs;
    }

    @Override
    public NoteDTO update(String id, NoteDTO noteDTO) {

        Note note = dtoNote.fromNoteDTO(noteDTO);

        Note savedNote = noteRepository.save(note);

        return dtoNote.fromNote(savedNote) ;
    }
}
