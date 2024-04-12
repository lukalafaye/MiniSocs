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

class TestCeationReseauSocial {
	
	private Utilisateur user;
	private String pseudoExec;
	private String nomReseau;
	private boolean ouvert;
	private String pseudoParticulier;
	private MiniSocs minisocs;
	private ReseauSocial rs;

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
	void creerReseauSocialTest1Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.creerReseauSocial(null, nomReseau, ouvert, pseudoParticulier));
	}

	@Test
	void creerReseauSocialTest1Jeu2() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.creerReseauSocial("", nomReseau, ouvert, pseudoParticulier));	}

	@Test
	void creerReseauSocialTest2Jeu1() {
		user.desactiverCompte();
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier));
	}

	@Test
	void creerReseauSocialTest3Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.creerReseauSocial(pseudoExec, null, ouvert, pseudoParticulier));
	}

	@Test
	void creerReseauSocialTest3Jeu2() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.creerReseauSocial(pseudoExec, "", ouvert, pseudoParticulier));
	}
	
	@Test
	void creerReseauSocialTest4Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, null));
	}
	
	@Test
	void creerReseauSocialTest4Jeu2() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, ""));
	}
	
	@Test
	void creerReseauSocialTest5Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, "modérateur");
		} catch (OperationImpossible e) {
			e.printStackTrace();
		}
		
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.creerReseauSocial(pseudoExec, nomReseau, ouvert, pseudoParticulier)); // rs existe déjà
	}
	
	@Test
	void creerReseauSocialTest6Jeu1() {
		try {
			rs = minisocs.creerReseauSocial(pseudoExec, nomReseau, false, pseudoParticulier);
		} catch (OperationImpossible e) {
			e.printStackTrace();
		}
		
		assertNotNull(rs);
		assertEquals(rs.getNom(), nomReseau);
		assertEquals(rs.getOuvert(), false);
		Membre m = rs.getMembrefromUtilisateur(user);
		assertEquals(m.getPseudoParticulier(), pseudoParticulier);
		assertTrue(rs.getMembres().contains(m));
		
	}
	
}