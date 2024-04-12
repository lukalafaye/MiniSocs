// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestAjouterUtilisateur {

	private MiniSocs minisocs;
	private Utilisateur user;
	
	@BeforeEach
	void setUp() {
		minisocs = new MiniSocs("Systeme Test");
	}

	@AfterEach
	void tearDown() {
		minisocs = null;
		user = null;
	}

	@Test
	void ajouterUtilisateurTest1Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.ajouterUtilisateur(null, "nom", "prénom", "courriel"));
	}

	@Test
	void ajouterUtilisateurTest1Jeu2() {
		Assertions.assertThrows(OperationImpossible.class, 
				() -> minisocs.ajouterUtilisateur("", "nom", "prénom", "courriel"));
	}

	@Test
	void ajouterUtilisateurTest2Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.ajouterUtilisateur("pseudo", null, "prénom", "courriel"));
	}

	@Test
	void ajouterUtilisateurTest2Jeu2() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.ajouterUtilisateur("pseudo", "", "prénom", "courriel"));
	}

	@Test
	void ajouterUtilisateurTest3Jeu1() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.ajouterUtilisateur("pseudo", "nom", null, "courriel"));
	}

	@Test
	void ajouterUtilisateurTest3Jeu2() {
		Assertions.assertThrows(OperationImpossible.class, 
				() -> minisocs.ajouterUtilisateur("pseudo", "nom", "", "courriel"));
	}

	@Test
	void ajouterUtilisateurTest4eu1() {
		Assertions.assertThrows(OperationImpossible.class, 
				() -> minisocs.ajouterUtilisateur("pseudo", "nom", "prénom", null));
	}

	@Test
	void ajouterUtilisateurTest4Jeu2() {
		Assertions.assertThrows(OperationImpossible.class, 
				() -> minisocs.ajouterUtilisateur("pseudo", "nom", "prénom", ""));
	}

	@Test
	void ajouterUtilisateurTest4Jeu3() {
		Assertions.assertThrows(OperationImpossible.class,
				() -> minisocs.ajouterUtilisateur("pseudo", "nom", "prénom", "malformé"));
	}

	@Test
	void ajouterUtilisateurTest5Jeu1() {
		try {
			user = minisocs.ajouterUtilisateur("pseudo", "nom", "prénom", "ok@courriel.fr");
		} catch (OperationImpossible e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assertions.assertNotNull(user);
		Assertions.assertEquals("pseudo", user.getPseudonyme());
		Assertions.assertEquals("nom", user.getNom());
		Assertions.assertEquals("prénom", user.getPrenom());
		Assertions.assertEquals("ok@courriel.fr", user.getCourriel());
		Assertions.assertEquals(EtatCompte.ACTIF, user.getEtatCompte());
	}
}