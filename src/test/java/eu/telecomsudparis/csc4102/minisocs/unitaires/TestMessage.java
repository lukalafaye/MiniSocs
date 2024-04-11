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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.Moderateur;
import eu.telecomsudparis.csc4102.minisocs.Membre;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;

public class TestMessage {
    private ReseauSocial rs;
    private Utilisateur utilisateur;
    private Membre membre;
    private Message m;
    
    @BeforeEach
    public void setUp() {
        rs = new ReseauSocial("ExampleRS", true);
        utilisateur = new Utilisateur("user1", "John", "Doe", "john@example.com");
        membre = new Membre("pseudo", utilisateur, rs);
        m = new Message("Test content", membre, EtatMessage.VERIFICATION_PENDING, rs);
    }
    
    @AfterEach
    public void tearDown() {
        rs = null;
        utilisateur = null;
        membre = null;
        m = null;
    }
    
    @Test
	void constructeurMessageTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Message(null, membre, EtatMessage.SENT, rs));
	}
    
    @Test
   	void constructeurMessageTest1Jeu2() {
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> new Message("", membre, EtatMessage.SENT, rs));
   	}
    
    @Test
   	void constructeurMessageTest2Jeu1() {
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> new Message("content", null, EtatMessage.SENT, rs));
   	}
    
    @Test
   	void constructeurMessageTest3Jeu1() {
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> new Message("content", membre, null, rs));
   	}
    
    @Test
   	void constructeurMessageTest4Jeu1() {
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> new Message("content", membre, EtatMessage.SENT, null));
   	}
    
    @Test
    public void constructeurMessageTest5Jeu1() {
        Message message = new Message("content", membre, EtatMessage.SENT, rs);
        assertNotNull(message);
        assertEquals("content", message.getContenu());
        assertEquals(EtatMessage.SENT, message.getEtat());
        assertEquals(rs, message.getReseauSocial());
        assertEquals(membre, message.getMembre());
    }
    
    @Test
   	void modifierStatutMessageTest1Jeu1() {
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> m.modifierStatutMessage(null));
   	}
    
    @Test
   	void modifierStatutMessageTest2Jeu1() {
   		m.modifierStatutMessage(EtatMessage.SENT);
   		assertEquals(EtatMessage.SENT, m.getEtat());
   	}
    
    @Test
   	void envoyerMessageTest1Jeu1() {
    	m.envoyerMessage();
    	assertEquals(EtatMessage.VERIFICATION_PENDING, m.getEtat());
   	}
    
    @Test
   	void envoyerMessageTest2Jeu1() {
    	Moderateur moderateur = new Moderateur("pseudo", utilisateur, rs);
        Message msg2 = new Message("Test content", moderateur, EtatMessage.VERIFICATION_PENDING, rs);
    	msg2.envoyerMessage();
    	assertEquals(EtatMessage.SENT, msg2.getEtat());
   	}
}
