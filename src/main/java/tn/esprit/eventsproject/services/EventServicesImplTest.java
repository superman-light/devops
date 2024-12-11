package tn.esprit.eventsproject.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.eventsproject.entities.Event;
import tn.esprit.eventsproject.entities.Participant;
import tn.esprit.eventsproject.repositories.EventRepository;
import tn.esprit.eventsproject.repositories.ParticipantRepository;

import java.util.Optional;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EventServicesImplTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private EventServicesImpl eventServices;

    @Test
    public void testAddAffectEvenParticipant() {
        // Préparer les données simulées
        Participant participant1 = new Participant();
        participant1.setIdPart(1);
        participant1.setNom("Doe");
        participant1.setPrenom("John");
        participant1.setEvents(null);

        Participant participant2 = new Participant();
        participant2.setIdPart(2);
        participant2.setNom("Smith");
        participant2.setPrenom("Jane");
        participant2.setEvents(new HashSet<>());

        Set<Participant> participants = new HashSet<>();
        participants.add(participant1);
        participants.add(participant2);

        Event event = new Event();
        event.setIdEvent(1);
        event.setDescription("Test Event");
        event.setParticipants(participants);

        // Simulation des appels des repositories
        when(participantRepository.findById(1)).thenReturn(Optional.of(participant1));
        when(participantRepository.findById(2)).thenReturn(Optional.of(participant2));
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Appeler la méthode à tester
        Event result = eventServices.addAffectEvenParticipant(event);

        // Vérifier les interactions
        verify(participantRepository, times(1)).findById(1);
        verify(participantRepository, times(1)).findById(2);
        verify(eventRepository, times(1)).save(event);

        // Vérifier les résultats
        assertEquals(2, result.getParticipants().size());
        assertEquals(event, result);
    }
}
