package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;



public class Moderateur extends Membre {

	public Moderateur(String pseudoParticulier, Utilisateur utilisateur) {
		super(pseudoParticulier, utilisateur);
	}
	
    @Override
    public void posterMessage(String contenu) throws OperationImpossible {
        if (contenu == null || contenu.isBlank()) {
            throw new OperationImpossible("Le contenu du message ne peut pas être null ou vide.");
        }
        Message nouveauMessage = new Message(contenu, this, EtatMessage.ACCEPTE, rs);
        rs.ajouterMessage(nouveauMessage);
    }

}