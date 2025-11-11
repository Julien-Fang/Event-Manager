package com.projet.event_manager.repositories;

import com.projet.event_manager.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventRepository  extends JpaRepository<Event, UUID> {
    @Query("""
    SELECT e
    FROM Event e
    WHERE e.createdBy = :createdBy
        AND e.categoryName = :categoryName
        AND e.eventDate = :eventDate
        AND UPPER(e.eventLocation) = UPPER(:eventLocation)
        AND UPPER(e.eventDescription) = UPPER(:eventDescription)
        AND e.maxCapacity = :maxCapacity
        AND e.image = :image""")
    List<Event> findEvent(UUID createdBy, LocalDateTime eventDate, String categoryName, String eventLocation, String eventDescription, int maxCapacity, byte[] image);

    @Query("""
    SELECT event
    FROM Event event
    WHERE UPPER(event.categoryName) LIKE UPPER(CONCAT('%',:categoryName,'%'))""")
    List<Event> findByCategoryName(@Param("categoryName") String categoryName);

    @Query("""
    SELECT event
    FROM Event event
    WHERE UPPER(event.eventName) LIKE UPPER(CONCAT('%',:eventName,'%'))""")
    List<Event> findByEventName(@Param("eventName") String eventName);

    @Query("""
    SELECT event
    FROM Event event
    WHERE UPPER(event.eventLocation) LIKE UPPER(CONCAT('%',:location,'%'))""")
    List<Event> findByLocation(@Param("location") String location);

    @Query("""
    SELECT event
    FROM Event event
    WHERE UPPER(event.eventDescription) LIKE UPPER(CONCAT('%',:description,'%'))""")
    List<Event> findByDescription(@Param("description") String description);

    @Query("""
    SELECT event
    FROM Event event, EventUser eventUser
    Where UPPER(event.categoryName) LIKE UPPER(CONCAT('%',:categoryName,'%'))
        OR UPPER(event.eventName) LIKE UPPER(CONCAT('%',:eventName,'%'))
        OR UPPER(event.eventLocation) LIKE UPPER(CONCAT('%',:eventLocation,'%'))
        OR UPPER(event.eventDescription) LIKE UPPER(CONCAT('%',:eventDescription,'%'))
        OR (UPPER(eventUser.pseudo) LIKE UPPER(CONCAT('%',:pseudo,'%')) AND eventUser.id = event.createdBy)""")
    List<Event> searchEvents(@Param("categoryName") String categoryName, @Param("eventName") String eventName, @Param("eventLocation") String eventLocation, @Param("eventDescription") String eventDescription, @Param("pseudo") String pseudo);

    @Query("""
    SELECT event
    FROM Event event
    WHERE event.createdBy = :createdBy""")
    List<Event> findByCreatedBy(@Param("createdBy") UUID createdBy);

    @Query("""
    SELECT user.id
    FROM EventUser user
    WHERE UPPER(user.pseudo) = UPPER(:pseudo)""")
    UUID findCreator(@Param("pseudo") String pseudo);

    @Query("""
    SELECT event
    FROM Event event
    ORDER BY event.eventDate""")
    List<Event> filterByDate();
}
