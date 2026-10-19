package dev.plesiosaur.neurosync_api.controller;

import dev.plesiosaur.neurosync_api.model.Deck;
import dev.plesiosaur.neurosync_api.model.DeckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    @Autowired
    private DeckRepository deckRepository;

    @GetMapping
    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

}
