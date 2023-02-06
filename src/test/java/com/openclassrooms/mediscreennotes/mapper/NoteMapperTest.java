package com.openclassrooms.mediscreennotes.mapper;

import com.openclassrooms.mediscreennotes.dto.NoteDTO;
import com.openclassrooms.mediscreennotes.model.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class NoteMapperTest {
    @InjectMocks
    private NoteMapper noteMapper;

    @Test
    public void fromNoteTest(){
        //GIVEN
        Note note = new Note("1", 1, "ADJOH", "The patient feels good");
        NoteDTO expected = new NoteDTO("1", 1, "ADJOH", "The patient feels good");

        //WHEN
        NoteDTO actual = noteMapper.fromNote(note);

        //THEN
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPatName(), actual.getPatName());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getObservation(), actual.getObservation());
    }

    @Test
    public void fromNoteDTOTest(){
        //GIVEN
        NoteDTO noteDTO= new NoteDTO("1", 1, "ADJOH", "The patient feels good");
        Note expected = new Note("1", 1, "ADJOH", "The patient feels good");

        //WHEN
        Note actual = noteMapper.fromNoteDTO(noteDTO);

        //THEN
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPatName(), actual.getPatName());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getObservation(), actual.getObservation());
    }
}
