package com.openclassrooms.mediscreennotes.repository;

import com.openclassrooms.mediscreennotes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note, String> {
    public Optional<Note> findById(String id);

    List<Note> findByPatId(int patId);
}
