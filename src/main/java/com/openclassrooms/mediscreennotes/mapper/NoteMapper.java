package com.openclassrooms.mediscreennotes.mapper;

import com.openclassrooms.mediscreennotes.dto.NoteDTO;
import com.openclassrooms.mediscreennotes.model.Note;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NoteMapper {
    public NoteDTO fromNote(Note note){
        NoteDTO noteDTO = new NoteDTO();
        BeanUtils.copyProperties(note, noteDTO);
        return noteDTO;
    }
    public Note fromNoteDTO(NoteDTO noteDTO){
        Note note = new Note();
        BeanUtils.copyProperties(noteDTO, note);
        return note;
    }
}
