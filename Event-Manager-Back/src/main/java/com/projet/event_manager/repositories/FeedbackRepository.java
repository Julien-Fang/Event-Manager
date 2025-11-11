package com.projet.event_manager.repositories;

import com.projet.event_manager.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FeedbackRepository  extends JpaRepository<Feedback, UUID> {
    List<Feedback> findAllByEventId(UUID eventId);
}
