package org.youjhin.hw12patternsspring.services.interfaces;

import org.youjhin.hw12patternsspring.model.WorkerEntity;

import java.util.List;

public interface WorkerService {

    List<WorkerEntity> findAll();

    WorkerEntity findById(Long id);

    void save(WorkerEntity worker);

    void assignTo(Long noteId, Long workerId);

    void takeOffTask(Long workerId);

}
