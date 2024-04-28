package com.example.javadevhw16.data;

import com.example.javadevhw16.data.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface NoteRepository extends JpaRepository<Note, UUID> {

}
