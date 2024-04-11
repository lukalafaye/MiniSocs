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

public class TestMembre {
    private ReseauSocial rs;
    private Utilisateur utilisateur;
    private Membre m;
    private Message msg;
    
    @BeforeEach
    public void setUp() {
        rs = new ReseauSocial("ExampleRS", true);
        utilisateur = new Utilisateur("user1", "John", "Doe", "john@example.com");
    }
    
    @AfterEach
    public void tearDown() {
        rs = null;
        utilisateur = null;
    }
    
    @Test
	void constructeurMembreTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Membre(null, utilisateur, rs));
	}
    
    @Test
	void constructeurMembreTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Membre("", utilisateur, rs));
	}
    
    @Test
   	void constructeurMembreTest2Jeu1() {
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> new Membre("Toto", null, rs));
   	}
    
    @Test
   	void constructeurMembreTest3Jeu1() {
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> new Membre("Toto", utilisateur, null));
   	}
    
    @Test
   	void constructeurMembreTest4Jeu1() {
   		m = new Membre("Toto", utilisateur, rs);
   		assertNotNull(m);
   		assertEquals("Toto", m.getPseudoParticulier());
   		assertEquals(utilisateur, m.getUtilisateur());
   		assertEquals(rs, m.getReseauSocial());
   	}
    
    @Test
   	void modererTest1Jeu1() {
    	m = new Membre("pseudoParticulier", utilisateur, rs);
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> m.moderer(null, EtatMessage.SENT));
   	}
    
    @Test
   	void modererTest2Jeu1() {
    	m = new Membre("pseudoParticulier", utilisateur, rs);
        msg = new Message("content", m, EtatMessage.VERIFICATION_PENDING, rs);

   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> m.moderer(msg, null));
   	}
    
    @Test
   	void modererTest3Jeu1() {
    	m = new Membre("pseudoParticulier", utilisateur, rs);
        msg = new Message("content", m, EtatMessage.VERIFICATION_PENDING, rs);

   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> m.moderer(msg, EtatMessage.SENT));
   	}
    
    @Test
   	void modererTest4Jeu1() {
    	m = new Moderateur("pseudoParticulier", utilisateur, rs);
        msg = new Message("content", m, EtatMessage.VERIFICATION_PENDING, rs);

        m.moderer(msg, EtatMessage.SENT);
        assertNotNull(msg);
        assertEquals(EtatMessage.SENT, msg.getEtat());
   	}  
}
