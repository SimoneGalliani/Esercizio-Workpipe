package com.workpipe.exercise.repository;

import com.workpipe.exercise.model.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtentiRepository extends JpaRepository<Utenti, String> {
}
