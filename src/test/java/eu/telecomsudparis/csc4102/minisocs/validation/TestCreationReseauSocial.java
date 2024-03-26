package eu.telecomsudparis.csc4102.minisocs.validation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eu.telecomsudparis.csc4102.minisocs.ReseauSocial;
import eu.telecomsudparis.csc4102.util.OperationImpossible;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreationReseauSocial {
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
    void testCreationReseauSocial1() {
        // Test case 1: Invalid network name
        assertThrows(IllegalArgumentException.class, () -> new ReseauSocial(null, true));
    }

    @Test
    void testCreationReseauSocial2() {
        // Test case 2: Invalid network name
        assertThrows(IllegalArgumentException.class, () -> new ReseauSocial("", true));
    }
}
