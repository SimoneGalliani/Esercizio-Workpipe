package com.workpipe.exercise.controller;

import com.workpipe.exercise.model.Utenti;
import com.workpipe.exercise.service.UtentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/utenti")
public class UtentiController {

    @Autowired
    private UtentiService utentiService;

    @PostMapping
    public Utenti createUtente(@RequestBody Utenti utente) {
        return utentiService.createUtente(utente);
    }

    @GetMapping
    public Page<Utenti> getAllUtenti(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return utentiService.getAllUtenti(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utenti> getUtenteById(@PathVariable String id) {
        Utenti utente = utentiService.getUtenteById(id).orElse(null);
        return ResponseEntity.ok(utente);
    }

    @PutMapping("/{id}")
    public Utenti updateUtente(@PathVariable String id, @RequestBody Utenti utenteDetails) {
        return utentiService.updateUtente(id, utenteDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable String id) {
        utentiService.deleteUtente(id);
        return ResponseEntity.noContent().build();
    }
}
