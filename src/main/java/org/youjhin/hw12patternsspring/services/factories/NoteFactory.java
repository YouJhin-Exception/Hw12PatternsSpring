package org.youjhin.hw12patternsspring.services.factories;

import org.youjhin.hw12patternsspring.enums.Urgency;
import org.youjhin.hw12patternsspring.model.NoteEntity;

public interface NoteFactory {
    NoteEntity createNote(String description, Urgency urgency);

}
