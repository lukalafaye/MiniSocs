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
		return this.nom;
	}
	
	public boolean getOuvert() {
		return this.ouvert;
	}
	
	public Set<Membre> getMembres() {
		return this.membres;
	}
	
	public List<Message> getMessages() {
		return this.messages;
	}
	
	public Membre getMembrefromUtilisateur(Utilisateur u) {
		if (u == null) {
			throw new IllegalArgumentException("utilisateur null");
		}
		System.out.println("POH\n");
		for (Membre m : this.membres) {
			System.out.println(u.getPseudonyme());
			System.out.println(m.getUtilisateur().getPseudonyme());
			System.out.println("POH\n");
			if (u.getPseudonyme().equals(m.getUtilisateur().getPseudonyme())) {
				return m;
			}
		}
		return null;
	}
	
    public Message getMessageFromId(double id) {
    	if (id < 0) {
	        throw new IllegalArgumentException("Id doit être positif");
	    }
        for (Message msg : this.messages) {
            if (msg.getId() == id) { 
                return msg;
            }
        }
        return null; 
    }
    
	public Membre ajouterMembre(Utilisateur u, String pseudoParticulier, boolean mod) {	

	    if ((pseudoParticulier == null) || (pseudoParticulier.isBlank())) {
	        throw new IllegalArgumentException("PseudoParticulier cannot be null or empty");
	    }

	    Membre m;
	    if (mod) {
	        m = new Moderateur(pseudoParticulier, u, this);
	        membres.add(m);
	    } else {
	        m = new Membre(pseudoParticulier, u, this);
	        membres.add(m);
	    }
	    assert invariant();

	    return m;
	}

	
    public Message ajouterMessage(String contenu, Membre m) {
    	Message nouveauMessage = new Message(contenu, m, EtatMessage.VERIFICATION_PENDING, this);
    	nouveauMessage.envoyerMessage();
        messages.add(nouveauMessage);
    	assert invariant();
    	return nouveauMessage;
    }
    
    public boolean invariant() {
        if (nom == null || nom.isBlank()) {
        	throw new IllegalStateException("Invariant : nom rs ne peut pas être null ou vide");
        }

        Objects.requireNonNull(membres, "Invariant violation: membres ne peut pas être null");
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
	
	public String toString() {
		return "nom : " + this.nom;
	}
}
