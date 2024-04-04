package eu.telecomsudparis.csc4102.minisocs.unitaires;

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

public class TestModererMessage {
    private Message m;
    private Membre membre;
    private ReseauSocial reseauSocial;

    @BeforeEach
    void setUp() {
        // Create a mock ReseauSocial object
        reseauSocial = new ReseauSocial("MockReseau", true);

        // Create a mock Utilisateur object for the Moderateur
        Utilisateur utilisateur = new Utilisateur("moderateur", "Doe", "John", "john@example.com");

        // Create a Moderateur Membre object
        membre = new Moderateur("moderateur", utilisateur, reseauSocial);

        // Create a Message object to be moderated
        m = new Message("Test content", membre, EtatMessage.VERIFICATION_PENDING, reseauSocial);
    }

    @AfterEach
    void tearDown() {
        // Clean up resources if needed
        m = null;
        membre = null;
        reseauSocial = null;
    }

}