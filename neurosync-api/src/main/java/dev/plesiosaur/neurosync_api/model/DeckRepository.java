package dev.plesiosaur.neurosync_api.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeckRepository extends JpaRepository<Deck, UUID> {


}
