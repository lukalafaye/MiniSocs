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

class TestPosterMessageRS {
	
	private Utilisateur user;
	private String pseudoExec;
	private String nomReseau;
	private boolean ouvert;
	private String pseudoParticulier;
	private MiniSocs minisocs;
	private ReseauSocial rs;
	private String contenu;
	private Message msg;

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
	}

	@Test
	void posterMessageRSTest1Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS(null, contenu, nomReseau));
	}
	
	@Test
	void posterMessageRSTest1Jeu2() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS("", contenu, nomReseau));
	}
	
	@Test
	void posterMessageRSTest2Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS(pseudoExec, null, nomReseau));
	}
	
	@Test
	void posterMessageRSTest2Jeu2() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS(pseudoExec, "", nomReseau));
	}
	
	@Test
	void posterMessageRSTest3Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS(pseudoExec, contenu, null));
	}
	
	@Test
	void posterMessageRSTest3Jeu2() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS(pseudoExec, contenu, ""));
	}
	
	@Test
	void posterMessageRSTest4Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.desactiverCompte();
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS(pseudoExec, contenu, nomReseau));
	}
	
	@Test
	void posterMessageRSTest5Jeu1() {
		// rs n'existe pas
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS(pseudoExec, contenu, nomReseau));
	}
	
	@Test
	void posterMessageRSTest6Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, false, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS(pseudoExec, contenu, nomReseau));
	}
	
	@Test
	void posterMessageRSTest7Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, false, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Utilisateur user2;
		try {
			user2 = minisocs.ajouterUtilisateur("nouveau", "nom", "prenom", "abc@xyz.com");
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// user2 pas sur rs
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.posterMessageRS("nouveau", contenu, nomReseau));
	}
	
	@Test
	void posterMessageRSTest8Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Utilisateur user2;
		try {
			user2 = minisocs.ajouterUtilisateur("nouveau", "nom", "prenom", "abc@xyz.com");
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// user2 membre simple sur le rs
		
		try {
			minisocs.ajouterMembreRS("nouveau", nomReseau, "user2", false);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			msg = minisocs.posterMessageRS("nouveau", contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // user pseudoExec est modérateur par construction du rs
		
		assertNotNull(msg);
		assertTrue(rs.getMessages().contains(msg));
		assertEquals(msg.getContenu(), contenu);
		assertEquals(msg.getEtat(), EtatMessage.VERIFICATION_PENDING);
	}
	
	@Test
	void posterMessageRSTest9Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// user modérateur sur le rs
		
		try {
			msg = minisocs.posterMessageRS(pseudoExec, contenu, nomReseau);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // user pseudoExec est modérateur par construction du rs
		
		assertNotNull(msg);
		assertTrue(rs.getMessages().contains(msg));
		assertEquals(msg.getContenu(), contenu);
		assertEquals(msg.getEtat(), EtatMessage.SENT);
	}
}