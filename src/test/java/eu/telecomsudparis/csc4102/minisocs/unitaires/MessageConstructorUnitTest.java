package eu.telecomsudparis.csc4102.minisocs.unitaires;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.Membre;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageConstructorUnitTest {
    private ReseauSocial rs;
    private Utilisateur utilisateur;
    private Membre membre;
    
    @BeforeEach
    public void setUp() {
        rs = new ReseauSocial("ExampleRS", true);
        utilisateur = new Utilisateur("user1", "John", "Doe", "john@example.com");
        membre = new Membre("pseudo", utilisateur, rs);
    }
    
    @AfterEach
    public void tearDown() {
        rs = null;
        utilisateur = null;
        membre = null;
    }
    
    @Test
    public void testMessageConstructor() {
        // Test case 1: Invalid content, valid accepte, valid reseauSocial, valid membre
        assertThrows(IllegalArgumentException.class, () -> new Message(null, membre, EtatMessage.ACCEPTE, rs));
        
        // Test case 2: Valid content, invalid accepte, valid reseauSocial, valid membre
        assertThrows(IllegalArgumentException.class, () -> new Message("", membre, EtatMessage.ACCEPTE, rs));
        
        // Test case 3: Valid content, valid accepte, invalid reseauSocial, valid membre
        assertThrows(IllegalArgumentException.class, () -> new Message("Test content", membre, EtatMessage.ACCEPTE, null));
        
        // Test case 4: Valid content, valid accepte, valid reseauSocial, invalid membre
        assertThrows(IllegalArgumentException.class, () -> new Message("Test content", null, EtatMessage.ACCEPTE, rs));
        
        // Test case 5: Valid content, valid accepte, valid reseauSocial, valid membre
        Message message = new Message("Test content", membre, EtatMessage.ACCEPTE, rs);
        assertNotNull(message);
        assertEquals("Test content", message.getContenu());
        assertEquals(EtatMessage.ACCEPTE, message.getEtat());
        assertEquals(rs, message.getReseauSocial());
        assertEquals(membre, message.getMembre());
    }
}
