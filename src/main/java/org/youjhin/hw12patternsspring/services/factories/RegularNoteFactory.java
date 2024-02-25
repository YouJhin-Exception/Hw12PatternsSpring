package org.youjhin.hw12patternsspring.services.factories;

import org.youjhin.hw12patternsspring.enums.Status;
import org.youjhin.hw12patternsspring.enums.Urgency;
import org.youjhin.hw12patternsspring.model.NoteEntity;

public class RegularNoteFactory implements NoteFactory{
    @Override
    public NoteEntity createNote(String description, Urgency urgency) {
        return new NoteEntity(description, Status.NOT_STARTED, Urgency.REGULAR);
    }
}
