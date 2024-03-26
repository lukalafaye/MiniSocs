package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class Membre {
	
	private String pseudoParticulier;
	private Utilisateur utilisateur;
	protected ReseauSocial rs;
	
	private Map<String, Message> messages;

	public Membre(final String pseudoParticulier, final Utilisateur utilisateur, ReseauSocial rs) {
		if (pseudoParticulier == null || pseudoParticulier.isBlank()) {
			throw new IllegalArgumentException("pseudoParticulier ne peut pas être null ou vide");
		}
		
		if (utilisateur == null || rs == null) {
			throw new IllegalArgumentException("utilisateur/rs ne peut pas être null");
		}
		
        if (utilisateur.pseudonyme == null || utilisateur.pseudonyme.isBlank()) {
        	throw new IllegalArgumentException("Pseudo de l'executeur non valide");
        }
        if (rs.nom == null || rs.nom.isBlank()) {
        	throw new IllegalArgumentException("Nom du Reseau non valide");
        }
		
		this.pseudoParticulier = pseudoParticulier;
		this.utilisateur = utilisateur;
		this.messages = new HashMap<>();
		this.rs = rs;
        assert invariant();
	}
	
	public void changePseudoParticulier(String pseudoParticulier) {
		if (pseudoParticulier == null || pseudoParticulier.isBlank()) {
			throw new IllegalArgumentException("pseudoParticulier ne peut pas être null ou vide");
		}
		
		if (utilisateur == null || rs == null) {
			throw new IllegalArgumentException("utilisateur/rs ne peut pas être null");
		}
		
        if (utilisateur.pseudonyme == null || utilisateur.pseudonyme.isBlank()) {
        	throw new IllegalArgumentException("Pseudo de l'executeur non valide");
        }
        if (rs.nom == null || rs.nom.isBlank()) {
        	throw new IllegalArgumentException("Nom du Reseau non valide");
        }
		
		this.pseudoParticulier = pseudoParticulier;
        invariant();
	}
	
	public String getPseudoParticulier() {
		return this.pseudoParticulier;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public boolean invariant() {
        if (pseudoParticulier == null || pseudoParticulier.isBlank()) {
        	throw new IllegalStateException("pseudoParticulier: ne peut pas être null ou vide");
        }
        
        if (utilisateur == null) {
        	throw new IllegalStateException("Utilisateur null");
        }
        
        if (rs == null) {
        	throw new IllegalStateException("RS null");
        }
        
        return true;
	}
	
    public void requestSendMessage(String messageContent) throws OperationImpossible {
        // Create a new message and add it to the messages map
    	this.rs.ajouterMessage(messageContent, this);
	    assert invariant();
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(pseudoParticulier);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Membre)) {
			return false;
		}
		Membre other = (Membre) obj;
		return Objects.equals(pseudoParticulier, other.pseudoParticulier);
	}
	
	public void moderer(Message m) {
		if (!(this instanceof Moderateur)) {
        	throw new UnsupportedOperationException("membre non mod");
		}
	    assert invariant();
	}
}
