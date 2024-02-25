package org.youjhin.hw12patternsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youjhin.hw12patternsspring.enums.Status;
import org.youjhin.hw12patternsspring.model.NoteEntity;



import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<NoteEntity,Long> {

    List<NoteEntity> findByStatus(Status status);
    Optional<NoteEntity> findById(Long noteId);

}
