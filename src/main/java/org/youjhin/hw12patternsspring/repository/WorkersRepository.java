package org.youjhin.hw12patternsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youjhin.hw12patternsspring.model.WorkerEntity;


public interface WorkersRepository extends JpaRepository<WorkerEntity, Long> {

}
