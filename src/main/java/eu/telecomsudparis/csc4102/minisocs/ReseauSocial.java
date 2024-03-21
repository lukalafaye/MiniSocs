package eu.telecomsudparis.csc4102.minisocs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import eu.telecomsudparis.csc4102.util.OperationImpossible;



public class ReseauSocial {
	/**
	 * le nom du RS.
	 */
	private final String nom;
	private boolean ouvert;
	private Set<Membre> membres;
	private Set<Moderateur> moderateurs; 
	private List<Message> messages;
	
	public ReseauSocial(final String nom, boolean ouvert) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("nom ne peut pas être null ou vide");
		}
		this.nom = nom;
		this.ouvert = ouvert;
		this.membres = new HashSet<>();
        this.messages = new ArrayList<>();
	}
	
	public String getNom() {
		return nom;
	}
	
	public boolean getOuvert() {
		return ouvert;
	}
	
	public Membre getMembrefromUtilisateur(Utilisateur u) {
		for (Membre m : this.membres) {
			if (u.getPseudonyme() == m.getUtilisateur().getPseudonyme()) {
				return m;
			}
		}
		return null;
	}
	
    public void ajouterMembre(String pseudo, Utilisateur u, String pseudoParticulier, boolean mod) throws OperationImpossible {
    	if (mod) {
            Moderateur m = new Moderateur(pseudo, u);
            if (pseudoParticulier != null && !pseudoParticulier.isBlank()) {
            	m.changePseudoParticulier(pseudoParticulier);
            }
            membres.add(m);
    	}
    	else {
            Membre m = new Membre(pseudo, u);
            if (pseudoParticulier != null && !pseudoParticulier.isBlank()) {
            	m.changePseudoParticulier(pseudoParticulier);
            }
            membres.add(m);
    	}
    }

	
    public void ajouterMessage(String contenu, Membre m) throws OperationImpossible {
    	if (m instanceof Moderateur) {
    		Message nouveauMessage = new Message(contenu, m, EtatMessage.ACCEPTE, this);
            messages.add(nouveauMessage);
    	}
    	else {
    		Message nouveauMessage = new Message(contenu, m, EtatMessage.VERIFICATION_PENDING, this);
            messages.add(nouveauMessage);
    	}
    }
    
	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ReseauSocial)) {
			return false;
		}
		ReseauSocial other = (ReseauSocial) obj;
		return Objects.equals(nom, other.nom);
	}

}
