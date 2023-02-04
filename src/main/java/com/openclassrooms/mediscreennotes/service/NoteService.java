package com.openclassrooms.mediscreennotes.service;

import com.openclassrooms.mediscreennotes.dto.NoteDTO;
import com.openclassrooms.mediscreennotes.exceptions.NotFoundNoteException;

import java.util.List;

public interface NoteService {
    NoteDTO addNote(NoteDTO noteDTO);
    NoteDTO getNote(String id) throws NotFoundNoteException;
    List<NoteDTO> getAllNote();
    void deleteNote(String id);
    List<NoteDTO> getAllNotePatient(int patId);

    NoteDTO update(String id, NoteDTO noteDTO) throws NotFoundNoteException;
}
