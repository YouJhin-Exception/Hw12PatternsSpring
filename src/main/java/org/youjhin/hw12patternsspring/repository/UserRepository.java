package org.youjhin.hw12patternsspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.youjhin.hw12patternsspring.model.User;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
