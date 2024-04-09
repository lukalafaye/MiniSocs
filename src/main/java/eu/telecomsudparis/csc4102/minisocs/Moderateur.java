package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class Moderateur extends Membre {

	public Moderateur(String pseudoParticulier, Utilisateur utilisateur, ReseauSocial rs) {
		super(pseudoParticulier, utilisateur, rs);
	}
    
    @Override 
	public void moderer(Message m, EtatMessage etat) {
		m.modifierStatutMessage(etat);
	}
}