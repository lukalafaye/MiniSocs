// CHECKSTYLE:OFF
package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.EtatCompte;
import eu.telecomsudparis.csc4102.minisocs.MiniSocs;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

class TestAjouterUtilisateur {
	private MiniSocs miniSocs;
	private String pseudo;
	private String nom;
	private String prenom;
	private String courriel;

	@BeforeEach
	void setUp() {
		miniSocs = new MiniSocs("MiniSocs");
		pseudo = "utilisateur1";
		nom = "nom1";
		prenom = "prenom1";
		courriel = "bon@courriel.fr";
	}

	@AfterEach
	void tearDown() {
		miniSocs = null;
		pseudo = null;
		nom = null;
		prenom = null;
		courriel = null;
	}

	@Test
	void ajouterUtilisateurTest1Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterUtilisateur(null, nom, prenom, courriel));
	}

	@Test
	void ajouterUtilisateurTest1Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterUtilisateur("", nom, prenom, courriel));
	}

	@Test
	void ajouterUtilisateurTest2Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterUtilisateur(pseudo, null, prenom, courriel));
	}

	@Test
	void ajouterUtilisateurTest2Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterUtilisateur(pseudo, "", prenom, courriel));
	}

	@Test
	void ajouterUtilisateurTest3Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterUtilisateur(pseudo, nom, null, courriel));
	}

	@Test
	void ajouterUtilisateurTest3Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.ajouterUtilisateur(pseudo, nom, "", courriel));
	}

	@Test
	void ajouterUtilisateurTest4Jeu1() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.ajouterUtilisateur(pseudo, nom, prenom, null));
	}

	@Test
	void ajouterUtilisateurTest4Jeu2() throws Exception {
		Assertions.assertThrows(OperationImpossible.class, () -> miniSocs.ajouterUtilisateur(pseudo, nom, prenom, ""));
	}

	@Test
	void ajouterUtilisateurTest4Jeu3() throws Exception {
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterUtilisateur(pseudo, nom, prenom, "mauvaiseadressecourriel"));
	}

	@Test
	void ajouterUtilisateurTest4Puis4() throws Exception {
		Assertions.assertTrue(miniSocs.listerUtilisateurs().isEmpty());
		miniSocs.ajouterUtilisateur(pseudo, nom, prenom, courriel);
		Assertions.assertFalse(miniSocs.listerUtilisateurs().isEmpty());
		Assertions.assertEquals(1, miniSocs.listerUtilisateurs().size());
		Assertions.assertTrue(miniSocs.listerUtilisateurs().get(0).contains(pseudo));
		Assertions.assertTrue(miniSocs.listerUtilisateurs().get(0).contains(nom));
		Assertions.assertTrue(miniSocs.listerUtilisateurs().get(0).contains(prenom));
		Assertions.assertTrue(miniSocs.listerUtilisateurs().get(0).contains(courriel));
		Assertions.assertTrue(miniSocs.listerUtilisateurs().get(0).contains(EtatCompte.ACTIF.toString()));
		Assertions.assertThrows(OperationImpossible.class,
				() -> miniSocs.ajouterUtilisateur(pseudo, nom, prenom, courriel));
	}
}
