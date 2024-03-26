package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.util.OperationImpossible;
import eu.telecomsudparis.csc4102.minisocs.Utilisateur;

import static org.junit.jupiter.api.Assertions.*;

public class TestAjoutMembreReseauSocial {
    private ReseauSocial reseauSocial;

    @BeforeEach
    void setUp() {
        reseauSocial = new ReseauSocial("Test", true);
    }

    @AfterEach
    void tearDown() {
        reseauSocial = null;
    }

    @Test
    void testAjoutMembreReseauSocial1() {
        // Test case 1: Invalid pseudo
        assertThrows(IllegalArgumentException.class, () -> reseauSocial.ajouterMembre(null, new Utilisateur("test", "Test", "User", "test@test.com"), "pseudoParticulier", true, reseauSocial));
    }

    @Test
    void testAjoutMembreReseauSocial2() {
        // Test case 2: Invalid utilisateur or RS null
        assertThrows(IllegalArgumentException.class, () -> reseauSocial.ajouterMembre("pseudo", null, "pseudoParticulier", true, reseauSocial));
    }

    @Test
    void testAjoutMembreReseauSocial3() {
        // Test case 3: Invalid pseudo of member
        assertThrows(IllegalArgumentException.class, () -> reseauSocial.ajouterMembre("pseudo", new Utilisateur("test", "Test", "User", "test@test.com"), null, true, reseauSocial));
    }
}