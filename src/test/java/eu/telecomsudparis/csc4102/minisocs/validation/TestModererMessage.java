package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.EtatMessage;
import eu.telecomsudparis.csc4102.minisocs.Message;
import eu.telecomsudparis.csc4102.minisocs.Moderateur;
import eu.telecomsudparis.csc4102.minisocs.Membre;
import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;


import static org.junit.jupiter.api.Assertions.*;

//CHECKSTYLE:OFF

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestModererMessage {
	
	private Utilisateur user;
	private Utilisateur user2;
	private String pseudoExec;
	private String nomReseau;
	private boolean ouvert;
	private String pseudoParticulier;
	private MiniSocs minisocs;
	private ReseauSocial rs;
	private String contenu;
	private Message msg;
	private Message msg1;
	private Message msg2;

	private double id;
	private EtatMessage etat;

	@BeforeEach
	void setUp() {
		pseudoExec = "exec";
		minisocs = new MiniSocs("Systeme Test");
		try {
			user = minisocs.ajouterUtilisateur(pseudoExec, "nom", "prenom", "abc@xyz.com");
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nomReseau = "nomRS";
		ouvert = true;
		pseudoParticulier = "pseudo particulier";
		contenu = "contenu";
		id = 0;
		etat = EtatMessage.HIDDEN;
	}

	@AfterEach
	void tearDown() {
		pseudoExec = null;
		nomReseau = null;
		pseudoParticulier = null;
		user = null;
		minisocs = null;
		rs = null;
		contenu = null;
		etat = null;
		user2 = null;
		msg=null;
	}

	@Test
	void modererMessageRSTest1Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage(null, id, nomReseau, etat));
	}
	
	@Test
	void modererMessageRSTest1Jeu2() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage("", id, nomReseau, etat));
	}
	
	@Test
	void modererMessageRSTest2Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage(pseudoExec, -1, nomReseau, etat));
	}
	
	@Test
	void modererMessageRSTest3Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage(pseudoExec, id, null, etat));
	}
	
	@Test
	void modererMessageRSTest3Jeu2() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage(pseudoExec, id, "", etat));
	}
	
	@Test
	void modererMessageRSTest4Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		user.desactiverCompte();
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage(pseudoExec, id, nomReseau, etat));
	}
	
	@Test
	void modererMessageRSTest5Jeu1() { // rs n'existe pas
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage(pseudoExec, id, nomReseau, etat));
	}
	
	@Test
	void modererMessageRSTest6Jeu1() { // rs pas ouvert
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		rs.setOuvert(false);
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage(pseudoExec, id, nomReseau, etat));
	}
	
	@Test
	void modererMessageRSTest7Jeu1() { // rs pas ouvert
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rs.setOuvert(false);
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage(pseudoExec, id, nomReseau, etat)); 
	}
	
	@Test
	void modererMessageRSTest8Jeu1() { 
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			user2 = minisocs.ajouterUtilisateur("pas sur rs", "nom", "prenom", "abc@xyz.com");
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			msg1 = minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			msg2 = minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage("pas sur rs", id, nomReseau, etat));
	}
	
	@Test
	void modererMessageRSTest9Jeu1() { 
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			user2 = minisocs.ajouterUtilisateur("nouveau", "nom", "prenom", "abc@xyz.com");
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.ajouterMembreRS("nouveau", nomReseau, "nouveau membre", false);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // not mod
		
		
		try {
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.modererMessage("nouveau", id, nomReseau, etat));
	}
	
	@Test
	void posterMessageRSTest10Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			msg1 = minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
			msg2 = minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		id = msg2.getId();
		
		try {
			minisocs.modererMessage(pseudoExec, id, nomReseau, etat);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(msg2);
		assertTrue(rs.getMessages().contains(msg2));
		assertEquals(msg2.getEtat(), etat);
	}
}