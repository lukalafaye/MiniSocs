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
	final String nom;
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
    	assert invariant();
		return nom;
	}
	
	public boolean getOuvert() {
    	assert invariant();
		return ouvert;
	}
	
	public Membre getMembrefromUtilisateur(Utilisateur u) {
		for (Membre m : this.membres) {
			if (u.getPseudonyme().equals(m.getUtilisateur().getPseudonyme())) {
				return m;
			}
		}
    	assert invariant();
		return null;
	}
    
	public void ajouterMembre(String pseudo, Utilisateur u, String pseudoParticulier, boolean mod, ReseauSocial rs) throws OperationImpossible {	
	    // Check preconditions
	    if (pseudo == null || pseudo.isEmpty() || u == null || rs == null) {
	        throw new IllegalArgumentException("Pseudo, Utilisateur, and ReseauSocial cannot be null or empty.");
	    }

	    if (mod && (pseudoParticulier == null || pseudoParticulier.isBlank())) {
	        throw new IllegalArgumentException("PseudoParticulier cannot be null or empty for a moderator.");
	    }

	    if (!mod && (pseudoParticulier == null || pseudoParticulier.isBlank())) {
	        throw new IllegalArgumentException("PseudoParticulier cannot be null or empty for a member.");
	    }

	    // Function logic
	    if (mod) {
	        Moderateur m = new Moderateur(pseudo, u, rs);
	        if (!pseudoParticulier.isBlank()) {
	            m.changePseudoParticulier(pseudoParticulier);
	        }
	        membres.add(m);
	    } else {
	        Membre m = new Membre(pseudo, u, rs);
	        if (!pseudoParticulier.isBlank()) {
	            m.changePseudoParticulier(pseudoParticulier);
	        }
	        membres.add(m);
	    }
	    
	    assert invariant();
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
    	
    	assert invariant();
    }
    
    public boolean invariant() {
        if (nom == null || nom.isBlank()) {
        	throw new IllegalStateException("Invariant : nom rs ne peut pas être null ou vide");
        }

        Objects.requireNonNull(membres, "Invariant violation: membres ne peut pas être null");
        Objects.requireNonNull(moderateurs, "Invariant violation: moderateurs ne peut pas être null");
        Objects.requireNonNull(messages, "Invariant violation: messages ne peut pas être null");
   
        return true;
    }
    
	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof ReseauSocial)) {
			return false;
		}
		ReseauSocial other = (ReseauSocial) obj;
		return this.hashCode() == other.hashCode();
	}

}
