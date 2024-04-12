package eu.telecomsudparis.csc4102.minisocs.unitaires;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.Membre;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

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

public class TestReseauSocial{
    private ReseauSocial rs;
    private Utilisateur utilisateur;
    private Message m;
    private Membre membre;
    
    @BeforeEach
    public void setUp() {
    	rs = new ReseauSocial("toto", true);
    	utilisateur = new Utilisateur("user1", "John", "Doe", "john@example.com");
    }
    
    @AfterEach
    public void tearDown() {
    	rs=null;
    	utilisateur = null;
    }
    
    @Test
	void constructeurReseauSocialTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new ReseauSocial(null, true));
	}
    
    @Test
	void constructeurReseauSocialTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new ReseauSocial("", true));
	}
    
    @Test
	void constructeurReseauSocialTest2Jeu1() {
    	rs = new ReseauSocial("toto", false);
    	
		assertNotNull(rs);
		assertNotNull(rs.getMembres());
		assertNotNull(rs.getMessages());
		assertEquals(rs.getNom(), "toto");
		
		assertEquals(rs.getOuvert(), false);
	}
    
    @Test
	void constructeurReseauSocialTest3Jeu1() {
		assertNotNull(rs);
		assertNotNull(rs.getMembres());
		assertNotNull(rs.getMessages());
		assertEquals(rs.getNom(), "toto");
		assertEquals(rs.getOuvert(), true);
	}
    
    @Test
	void getMembreFromUtilisateurTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> rs.getMembrefromUtilisateur(null));
	}
    
    @Test
	void getMembreFromUtilisateurTest2Jeu1() {
		Membre m = rs.getMembrefromUtilisateur(utilisateur);
		// utilisateur pas associé à un membr sur le rs
		assertNull(m);
	}
    
    @Test
	void getMembreFromUtilisateurTest3Jeu1() {
		Membre m1 = rs.ajouterMembre(utilisateur, "pseudo", true);
		Membre m = rs.getMembrefromUtilisateur(utilisateur);
		
		assertNotNull(m);
		assertEquals(m, m1);
	}
    
    @Test
   	void getMessageFromIdTest1Jeu1() {
   		Assertions.assertThrows(IllegalArgumentException.class,
   				() -> rs.getMessageFromId(-1));
   	}
    
    @Test
   	void getMessageFromIdTest2Jeu1() {	
   	    Message m = rs.getMessageFromId(0.0); // Inexistant
   		assertNull(m);
   	}
       
    @Test
   	void getMessageFromIdTest3Jeu1() {
   		Membre membre = rs.ajouterMembre(utilisateur, "pseudo", true);
   	    Message m0 = rs.ajouterMessage("contenu pour id0", membre); 
   	    Message m1 = rs.ajouterMessage("contenu pour id1", membre); 
   	    
   	    Message mtest0 = rs.getMessageFromId(0.0);
   	    Message mtest1 = rs.getMessageFromId(1.0);
   	 
   	    assertEquals(mtest0, m0);
   	    assertEquals(mtest1, m1);
   	    
   	}
    
    @Test
   	void ajouterMembreTest1Jeu1() {	
    	Assertions.assertThrows(IllegalArgumentException.class,
				() -> rs.ajouterMessage(null, membre));
   	}
    
    @Test
   	void ajouterMembreTest1Jeu2() {	
    	Assertions.assertThrows(IllegalArgumentException.class,
				() -> rs.ajouterMessage("", membre));
   	}
    
    @Test
   	void ajouterMembreTest2Jeu1() {
    	
    	Assertions.assertThrows(IllegalArgumentException.class,
				() -> rs.ajouterMessage(null, membre));
   	}
    
    @Test
   	void ajouterMembreTest3Jeu1() {	
    	rs.ajouterMembre(utilisateur, "deja pris", false);
    	
    	Assertions.assertThrows(IllegalArgumentException.class,
				() -> rs.ajouterMembre(utilisateur, "deja pris", false));
   	}
    
    @Test
   	void ajouterMembreTest4Jeu1() {	
    	Membre m = rs.ajouterMembre(utilisateur, "moderateur", false);
    	assertNotNull(m);
    	assertTrue(rs.getMembres().contains(m));
        assertTrue(m instanceof Membre);
        assertFalse(m instanceof Moderateur);
   	}
    
    @Test
   	void ajouterMembreTest5Jeu1() {	
    	Membre m = rs.ajouterMembre(utilisateur, "moderateur", true);
    	assertNotNull(m);
    	assertTrue(rs.getMembres().contains(m));
        assertTrue(m instanceof Moderateur);
   	}
    
    @Test
   	void ajouterMessageTest1Jeu1() {	
    	Assertions.assertThrows(IllegalArgumentException.class,
				() -> rs.ajouterMessage(null, membre));
   	}
    
    @Test
   	void ajouterMessageTest1Jeu2() {	
    	Assertions.assertThrows(IllegalArgumentException.class,
				() -> rs.ajouterMessage("", membre));
   	}
    
    @Test
   	void ajouterMessageTest2Jeu1() {	
    	Assertions.assertThrows(IllegalArgumentException.class,
				() -> rs.ajouterMessage("ok", null));
   	}
    
    
    @Test
   	void ajouterMessageTest3Jeu1() {	
    	membre = rs.ajouterMembre(utilisateur, "non moderateur", false);
    	m = rs.ajouterMessage("ok", membre);
    	
    	assertNotNull(m);
    	assertTrue(rs.getMessages().contains(m));
    	assertEquals(m.getContenu(), "ok");
    	assertEquals(m.getEtat(), EtatMessage.VERIFICATION_PENDING);
   	}
    
    @Test
   	void ajouterMessageTest4Jeu1() {	
    	Membre moderateur = rs.ajouterMembre(utilisateur, "moderateur", true); 
    	m = rs.ajouterMessage("ok", moderateur);
    	
    	assertNotNull(m);
    	assertTrue(rs.getMessages().contains(m));
    	assertEquals(m.getContenu(), "ok");
    	assertEquals(m.getEtat(), EtatMessage.SENT);
   	}
}
