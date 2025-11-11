package com.projet.event_manager.repositories;

import com.projet.event_manager.models.EventUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventUserRepository extends JpaRepository<EventUser, UUID> {
    EventUser findByPseudo(String pseudo);
}