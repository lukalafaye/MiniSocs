package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.Membre;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.Moderateur;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestSendMessage {

    private Membre member;
    private Moderateur moderator;
	ReseauSocial rs = new ReseauSocial("ExampleRS", true);

    @BeforeEach
    public void setUp() {
        // Initialize resources before each test
    	Utilisateur user = new Utilisateur("pseudo", "nom", "prénom", "toto@xyz.com");
        member = new Membre("username", user , rs);
		moderator = new Moderateur("moderator", user, rs); 
    }

    @Test
    public void testPostMessageForMember() {
        // Create a message with a regular member
        Message message = new Message("Test message", member, EtatMessage.SENT, rs);

        // Call send_message() method
        message.send_message();

        // Verify that the message state is VERIFICATION_PENDING
        assertEquals(EtatMessage.VERIFICATION_PENDING, message.getEtat());
    }

    @Test
    public void testPostMessageForModerator() {
        // Create a message with a moderator
        Message message = new Message("Test message", moderator, EtatMessage.SENT, rs);

        // Call send_message() method
        message.send_message();

        // Verify that the message state is ACCEPTE
        assertEquals(EtatMessage.ACCEPTE, message.getEtat());
    }

    @Test
    public void testPostMessageWithNullContentForMember() {
        // Create a message with null content for a regular member
        assertThrows(IllegalArgumentException.class, () -> new Message(null, member, EtatMessage.SENT, rs));
    }

    @Test
    public void testPostMessageWithBlankContentForMember() {
        // Create a message with blank content for a regular member
        assertThrows(IllegalArgumentException.class, () -> new Message("", member, EtatMessage.SENT, rs));
    }

    @Test
    public void testPostMessageWithNullContentForModerator() {
        // Create a message with null content for a moderator
        assertThrows(IllegalArgumentException.class, () -> new Message(null, moderator, EtatMessage.SENT, rs));
    }

    @Test
    public void testPostMessageWithBlankContentForModerator() {
        // Create a message with blank content for a moderator
        assertThrows(IllegalArgumentException.class, () -> new Message("", moderator, EtatMessage.SENT, rs));
    }

    @AfterEach
    public void tearDown() {
        // Clean up resources after each test
    }
}