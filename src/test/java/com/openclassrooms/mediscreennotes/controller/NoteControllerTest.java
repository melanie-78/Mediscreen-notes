package com.openclassrooms.mediscreennotes.controller;

import com.openclassrooms.mediscreennotes.dto.NoteDTO;
import com.openclassrooms.mediscreennotes.service.NoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    public void testGetNotes() throws Exception {
        List<NoteDTO> list = new ArrayList<>();

        when(noteService.getAllNote()).thenReturn(list);

        this.mockMvc
                .perform(get("/notes"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddNote() throws Exception {
        NoteDTO noteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");
        NoteDTO savedNoteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");

        when(noteService.addNote(noteDTO)).thenReturn(noteDTO);

        this.mockMvc
                .perform(post("/note/add")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNotesPatient() throws Exception {
        Integer patId = 1;
        List<NoteDTO> list = new ArrayList<>();

        when(noteService.getAllNotePatient(patId)).thenReturn(list);

        this.mockMvc
                .perform(get("/notes/"+ patId))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateNote() throws Exception {
       String id = "1";

        NoteDTO noteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");
        NoteDTO savedNoteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");

        when(noteService.update(id, noteDTO)).thenReturn(savedNoteDTO);

        this.mockMvc
                .perform(put("/note/update/" + id)
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNote() throws Exception {
        String id = "1";

        NoteDTO savedNoteDTO = new NoteDTO(null, 1, "ADJOH", "The patient feels good");

        when(noteService.getNote(id)).thenReturn(savedNoteDTO);

        this.mockMvc
                .perform(get("/note/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteNote() throws Exception {
        String id = "1";

        doNothing().when(noteService).deleteNote(id);

        mockMvc.perform(delete("/note/delete/"+id))
                .andExpect(status().isOk());

    }
}
