package com.openclassrooms.mediscreennotes;

import com.openclassrooms.mediscreennotes.dto.NoteDTO;
import com.openclassrooms.mediscreennotes.mapper.NoteMapper;
import com.openclassrooms.mediscreennotes.model.Note;
import com.openclassrooms.mediscreennotes.repository.NoteRepository;
import com.openclassrooms.mediscreennotes.service.NoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MediscreenNotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenNotesApplication.class, args);
	}

	/*//@Bean
	CommandLineRunner commandLineRunner(NoteService noteService) {
		return args -> {
			NoteDTO noteDTO5 =
					new NoteDTO(1, "Adjoh", "c'est pas moi");

			noteService.addNote(noteDTO5);
		};
	}
	//@Bean
	*//*CommandLineRunner commandLineRunner(NoteService noteService) {
		return args -> {
			NoteDTO noteDTO =
					new NoteDTO(1, "Adjoh", "paludismmeeeeee ++++++++");
			NoteDTO noteDTO1 =
					new NoteDTO(2, "Adjoh", "allez vas y on lâche pas");
			NoteDTO noteDTO2 =
					new NoteDTO(3, "Adriel", "paludismmeeeeee");
			NoteDTO noteDTO3 =
					new NoteDTO(4, "Tounga", "allez vas y");


			noteService.addNote(noteDTO);
			noteService.addNote(noteDTO1);
			noteService.addNote(noteDTO2);
			noteService.addNote(noteDTO3);

		};
	}*/
}
