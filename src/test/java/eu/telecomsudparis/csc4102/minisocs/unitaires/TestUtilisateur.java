// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.unitaires;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TestUtilisateur {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void constructeurUtilisateurTest1Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Utilisateur(null, "nom", "prénom", "courriel"));
	}

	@Test
	void constructeurUtilisateurTest1Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Utilisateur("", "nom", "prénom", "courriel"));
	}

	@Test
	void constructeurUtilisateurTest2Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Utilisateur("pseudo", null, "prenom", "courriel"));
	}

	@Test
	void constructeurUtilisateurTest2Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Utilisateur("pseudo", "", "prenom", "courriel"));
	}

	@Test
	void constructeurUtilisateurTest3Jeu1() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Utilisateur("pseudo", "nom", null, "courriel"));
	}

	@Test
	void constructeurUtilisateurTest3Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Utilisateur("pseudo", "nom", "", "courriel"));
	}

	@Test
	void constructeurUtilisateurTest4eu1() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Utilisateur("pseudo", "nom", "prenom", null));
	}

	@Test
	void constructeurUtilisateurTest4Jeu2() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> new Utilisateur("pseudo", "nom", "prenom", ""));
	}

	@Test
	void constructeurUtilisateurTest4Jeu3() {
		Assertions.assertThrows(IllegalArgumentException.class,
				() -> new Utilisateur("pseudo", "nom", "prenom", "adressecourrielmalformée"));
	}

	@Test
	void constructeurUtilisateurTest5Jeu1() {
		Utilisateur utilisateur = new Utilisateur("pseudo", "nom", "prénom", "bon@courriel.fr");
		
		Assertions.assertNotNull(utilisateur);
		Assertions.assertEquals("pseudo", utilisateur.getPseudonyme());
		Assertions.assertEquals("nom", utilisateur.getNom());
		Assertions.assertEquals("prénom", utilisateur.getPrenom());
		Assertions.assertEquals("bon@courriel.fr", utilisateur.getCourriel());
		Assertions.assertEquals(EtatCompte.ACTIF, utilisateur.getEtatCompte());
	}
	
	@Test
	void desactiverTest1() {
		Utilisateur utilisateur = new Utilisateur("pseudo", "nom", "prénom", "bon@courriel.fr");
		utilisateur.setEtatCompte(EtatCompte.BLOQUE);

		Assertions.assertEquals(EtatCompte.BLOQUE, utilisateur.getEtatCompte());
		
		Assertions.assertThrows(IllegalStateException.class, () -> utilisateur.desactiverCompte());
	}

	@Test
	void desactiverTest2Jeu1Puis2() {
		Utilisateur utilisateur = new Utilisateur("pseudo", "nom", "prénom", "bon@courriel.fr");
		Assertions.assertNotEquals(EtatCompte.BLOQUE, utilisateur.getEtatCompte());
		
		utilisateur.desactiverCompte();
		Assertions.assertEquals(EtatCompte.DESACTIVE, utilisateur.getEtatCompte());
		
		utilisateur.desactiverCompte(); // idempotence
		Assertions.assertEquals(EtatCompte.DESACTIVE, utilisateur.getEtatCompte());
	}
}