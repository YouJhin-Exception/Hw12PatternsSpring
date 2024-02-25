package org.youjhin.hw12patternsspring.services.interfaces;

import org.youjhin.hw12patternsspring.enums.Status;
import org.youjhin.hw12patternsspring.enums.Urgency;
import org.youjhin.hw12patternsspring.model.NoteEntity;



import java.util.List;

public interface NoteService {
    List<NoteEntity> getAllNotes();

    List<NoteEntity> getNotesByStatus(Status status);

    void createNote(String description, Urgency urgency);

    void updateStatusNote(Long id, Status newStatus);

    void deleteNote(Long id);

    NoteEntity findById(Long noteId);
}
