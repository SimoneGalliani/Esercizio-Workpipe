package com.workpipe.exercise.service;

import com.workpipe.exercise.model.Utenti;
import com.workpipe.exercise.repository.UtentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtentiService {

    @Autowired
    private UtentiRepository utentiRepository;

    public Utenti createUtente(Utenti utente) {
        return utentiRepository.save(utente);
    }

    public Page<Utenti> getAllUtenti(Pageable pageable) {
        return utentiRepository.findAll(pageable);
    }

    public Optional<Utenti> getUtenteById(String id) {
        return utentiRepository.findById(id);
    }

    public Utenti updateUtente(String id, Utenti utenteDetails) {
        Utenti utente = utentiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        // Aggiorna i dettagli dell'utente
        utente.setNome(utenteDetails.getNome());
        utente.setCognome(utenteDetails.getCognome());
        utente.setEmail(utenteDetails.getEmail());
        utente.setDataNascita(utenteDetails.getDataNascita());
        return utentiRepository.save(utente);
    }

    public void deleteUtente(String id) {
        utentiRepository.deleteById(id);
    }
}
