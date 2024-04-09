package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

public class Membre {
	
	private String pseudoParticulier;
	private final Utilisateur utilisateur;
	protected final ReseauSocial rs;
	
	private Map<String, Message> messages;

	public Membre(final String pseudoParticulier, final Utilisateur utilisateur, final ReseauSocial rs) {	
		if (pseudoParticulier == null || pseudoParticulier.isBlank()) {
			throw new IllegalArgumentException("pseudoParticulier ne peut pas être null ou vide");
		}
		
		if (utilisateur == null || rs == null) {
			throw new IllegalArgumentException("utilisateur/rs ne peut pas être null");
		}
		
        if (utilisateur.getPseudonyme() == null || utilisateur.getPseudonyme().isBlank()) {
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
	
	public void changePseudoParticulier(final String pseudoParticulier) {
		if (pseudoParticulier == null || pseudoParticulier.isBlank()) {
			throw new IllegalArgumentException("pseudoParticulier ne peut pas être null ou vide");
		}
		
		if (utilisateur == null || rs == null) {
			throw new IllegalArgumentException("utilisateur/rs ne peut pas être null");
		}
		
        if (utilisateur.getPseudonyme() == null || utilisateur.getPseudonyme().isBlank()) {
        	throw new IllegalArgumentException("Pseudo de l'executeur non valide");
        }
        if (rs.nom == null || rs.nom.isBlank()) {
        	throw new IllegalArgumentException("Nom du Reseau non valide");
        }
		
		this.pseudoParticulier = pseudoParticulier;
		assert invariant();
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
	
    public void addMessage(Message message, final String contenu) {
    	this.messages.put(contenu, message);
	    assert invariant();
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(pseudoParticulier);
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Membre)) {
			return false;
		}
		Membre other = (Membre) obj;
		return other.hashCode() == this.hashCode();
	}
	
	@Override 
	public String toString() {
		return "utilisateur : " + this.utilisateur + "\npseudo membre : " + this.pseudoParticulier + "\nOn socia : " + this.rs;
	}
	
	public void moderer(final Message m, EtatMessage etat) {
		if (!(this instanceof Moderateur)) {
        	throw new UnsupportedOperationException("membre non mod");
		}
	    assert invariant();
	}
}