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

    /**
     *this permits to insert a note in database
     * @param noteDTO data of note
     * @return noteDTO saved
     */
    @Override
    public NoteDTO addNote(NoteDTO noteDTO) {
        Note note = dtoNote.fromNoteDTO(noteDTO);
        Note insert = noteRepository.insert(note);

        return  dtoNote.fromNote(insert);
    }

    /**
     * @param id is the identifier of a note
     * @return data about note identified by id
     */
    @Override
    public NoteDTO getNote(String id) {
        Note noteById = noteRepository.findById(id)
                        .orElseThrow(()->new NotFoundNoteException("This note doesn't exist in database"));
        return dtoNote.fromNote(noteById);
    }

    /**
     *
     * @return a list of data about notes saved in database
     */
    @Override
    public List<NoteDTO> getAllNote() {
        List<Note> all = noteRepository.findAll();
        List<NoteDTO> allNotes = all.stream().map(note -> {
            NoteDTO noteDTO = dtoNote.fromNote(note);
            return noteDTO;
        }).collect(Collectors.toList());
        return allNotes;
    }

    /**
     * this permits to delete a note by id
     * @param id the identifier of the note
     */
    @Override
    public void deleteNote(String id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NotFoundNoteException("This note doesn't exist in database"));
        noteRepository.delete(note);
    }

    /**
     *
     * @param patId is the identifier's patient
     * @return a list of noteDTO of this patient
     */
    @Override
    public List<NoteDTO> getAllNotePatient(int patId) {
        List<Note> notes = noteRepository.findByPatId(patId);
        List<NoteDTO> noteDTOs = notes.stream().map(note -> {
            NoteDTO noteDTO = dtoNote.fromNote(note);
            return noteDTO;
        }).collect(Collectors.toList());

        return noteDTOs;
    }

    /**
     * this permits to update note's data
     * @param id is the identifier of a patient
     * @param noteDTO data about note identified by id
     * @return noteDTO updated
     */
    @Override
    public NoteDTO update(String id, NoteDTO noteDTO) {

        Note note = dtoNote.fromNoteDTO(noteDTO);

        Note savedNote = noteRepository.save(note);

        return dtoNote.fromNote(savedNote) ;
    }
}
