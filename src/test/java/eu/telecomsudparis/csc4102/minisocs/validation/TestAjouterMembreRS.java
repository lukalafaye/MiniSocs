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

class TestAjouterMembreRS {
	
	private Utilisateur user;
	private String pseudoExec;
	private String nomReseau;
	private boolean ouvert;
	private String pseudoParticulier;
	private MiniSocs minisocs;
	private ReseauSocial rs;
	private boolean mod;

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
		mod = true;
	}

	@AfterEach
	void tearDown() {
		pseudoExec = null;
		nomReseau = null;
		pseudoParticulier = null;
		user = null;
		minisocs = null;
		rs = null;
	}

	@Test
	
	void  ajouterMembreRSTest1Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> minisocs.ajouterMembreRS(null, nomReseau, pseudoParticulier, mod));
	}

	@Test
	void ajouterMembreRSTest1Jeu2() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> minisocs.ajouterMembreRS("", nomReseau, pseudoParticulier, mod));
	}
	
	
	@Test
	void ajouterMembreRSTest2Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.desactiverCompte();
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> minisocs.ajouterMembreRS(pseudoExec, nomReseau, pseudoParticulier, mod));
	}

	
	@Test
	void ajouterMembreRSTest3Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user.desactiverCompte();
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> minisocs.ajouterMembreRS(pseudoExec, null, pseudoParticulier, mod));
	}

	@Test
	void ajouterMembreRSTest3Jeu2() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> minisocs.ajouterMembreRS(pseudoExec, "", pseudoParticulier, mod));
	}
	
	@Test
	void ajouterMembreRSTest4Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> minisocs.ajouterMembreRS(pseudoExec, nomReseau, null, mod));
	}
	
	@Test
	void ajouterMembreRSTest4Jeu2() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> minisocs.ajouterMembreRS(pseudoExec, nomReseau, "", mod));
	}
	
	@Test
	void ajouterMembreRSTest5Jeu1() { // rs n'existe pas
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> minisocs.ajouterMembreRS(pseudoExec, nomReseau, pseudoParticulier, mod));
	}

	@Test
	void creerReseauSocialTest6Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			minisocs.ajouterMembreRS(pseudoExec, nomReseau, "nouveau membre", mod);
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Membre m = rs.getMembrefromUtilisateur(user);
		assertEquals(m.getPseudoParticulier(), "nouveau membre");
		assertTrue(rs.getMembres().contains(m));
		
		if (mod) {
			assertTrue(m instanceof Moderateur);
		}
	}	
}