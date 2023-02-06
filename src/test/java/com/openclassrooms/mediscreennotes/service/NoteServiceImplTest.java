package com.openclassrooms.mediscreennotes.service;

import com.openclassrooms.mediscreennotes.dto.NoteDTO;
import com.openclassrooms.mediscreennotes.mapper.NoteMapper;
import com.openclassrooms.mediscreennotes.model.Note;
import com.openclassrooms.mediscreennotes.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTest {
    @InjectMocks
    private NoteServiceImpl noteService;

    @Mock
    private NoteMapper dtoMapper;

    @Mock
    private NoteRepository noteRepository;

    @Test
    public void getAllNoteTest() {
        //GIVEN
        Note note = new Note(null, 1, "ADJOH", "The patient feels good");
        Note note1 = new Note(null, 1, "TOUNGA", "The patient feels good");

        List<Note> list = new ArrayList<>();
        list.add(note);
        list.add(note1);

        NoteDTO noteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");
        NoteDTO noteDTO1 = new NoteDTO(null, 1, "TOUNGA", "The patient feels good");

        when(noteRepository.findAll()).thenReturn(list);

        when(dtoMapper.fromNote(note)).thenReturn(noteDTO);
        when(dtoMapper.fromNote(note1)).thenReturn(noteDTO1);


        //WHEN
        List<NoteDTO> listResult = noteService.getAllNote();

        //THEN
        assertTrue(listResult.size() == 2);
        verify(noteRepository, Mockito.times(1)).findAll();
        verify(dtoMapper, Mockito.times(2)).fromNote(any());
    }

    @Test
    public void getAllNotePatientTest() {
        //GIVEN
        Integer id =1;
        Note note = new Note(null, 1, "ADJOH", "The patient feels good");
        Note note1 = new Note(null, 1, "ADJOH", "The patient feels good");

        List<Note> list = new ArrayList<>();
        list.add(note);
        list.add(note1);

        NoteDTO noteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");
        NoteDTO noteDTO1 = new NoteDTO(null, 1, "ADJOH", "The patient feels good");

        when(noteRepository.findByPatId(id)).thenReturn(list);

        when(dtoMapper.fromNote(note)).thenReturn(noteDTO);
        when(dtoMapper.fromNote(note1)).thenReturn(noteDTO1);


        //WHEN
        List<NoteDTO> listResult = noteService.getAllNotePatient(id);

        //THEN
        assertTrue(listResult.size() == 2);
        verify(noteRepository, Mockito.times(1)).findByPatId(id);
    }

    @Test
    public void addNoteTest() {
        //GIVEN
        NoteDTO noteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");
        Note note = new Note("1", 1, "ADJOH", "The patient feels good");

        Note savedNote = new Note("1", 1, "ADJOH", "The patient feels good");
        NoteDTO savedNoteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");

        when(dtoMapper.fromNoteDTO(noteDTO)).thenReturn(note);
        when(noteRepository.insert(note)).thenReturn(savedNote);
        when(dtoMapper.fromNote(savedNote)).thenReturn(savedNoteDTO);

        //WHEN
        NoteDTO actual = noteService.addNote(noteDTO);

        //THEN
        verify(noteRepository, Mockito.times(1)).insert(note);
        assertEquals(savedNoteDTO.getId(), actual.getId());
    }

    @Test
    public void updateTest() {
        //GIVEN
        String id = "1";
        NoteDTO noteDTO = new NoteDTO("1", 1, "TOUNGA", "The patient feels good");
        Note note = new Note("1", 1, "ADJOH", "The patient feels good");

        when(dtoMapper.fromNoteDTO(noteDTO)).thenReturn(note);

        Note savedNote = new Note("1", 1, "ADJOH", "The patient feels good");
        NoteDTO savedNoteDTO = new NoteDTO("1", 1, "ADJOH", "The patient feels good");

        when(dtoMapper.fromNoteDTO(noteDTO)).thenReturn(note);
        when(noteRepository.save(note)).thenReturn(savedNote);
        when(dtoMapper.fromNote(savedNote)).thenReturn(savedNoteDTO);

        //WHEN
        NoteDTO actual = noteService.update(id, noteDTO);

        //THEN
        verify(noteRepository, Mockito.times(1)).save(note);
        assertEquals(savedNoteDTO.getPatName(), actual.getPatName());

    }

    @Test
    public void deleteNoteTest() {
        //GIVEN
        String  id = "1";
        Note note = new Note("1", 1, "ADJOH", "The patient feels good");

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        doNothing().when(noteRepository).delete(note);

        //WHEN
        noteService.deleteNote(id);

        //THEN
        verify (noteRepository, Mockito.times(1)).findById(id);
        verify(noteRepository,Mockito.times(1)).delete(note);
    }

    @Test
    public void getNoteTest() {
        //GIVEN
        String id = "1";
        Note note = new Note("1", 1, "ADJOH", "The patient feels good");

        NoteDTO noteDTO = new NoteDTO("1", 1, "ADJOH", "The patient feels good");

        when(noteRepository.findById(id)).thenReturn(Optional.of(note));
        when(dtoMapper.fromNote(note)).thenReturn(noteDTO);

        //WHEN
        NoteDTO actual = noteService.getNote(id);

        //THEN
        assertTrue(actual.getPatName().equals("ADJOH"));
        verify(noteRepository, Mockito.times(1)).findById(id);
    }
}
