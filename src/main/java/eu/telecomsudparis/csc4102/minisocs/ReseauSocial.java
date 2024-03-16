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
        this.moderateurs = new HashSet<>();
        this.messages = new ArrayList<>();
	}
	
	public String getNom() {
		return nom;
	}
	
    public void ajouterMembre(Membre membre) throws OperationImpossible {
    	if (membre.getPseudoParticulier() == null || membre.getPseudoParticulier().isBlank()) {
            throw new OperationImpossible("Pseudo non valide.");
        }
        Utilisateur utilisateur = membre.getUtilisateur();
        if (utilisateur == null || utilisateur.getEtatCompte() != EtatCompte.ACTIF) {
            throw new OperationImpossible("L'utilisateur n'est pas actif ou n'existe pas.");
        }
        if (!this.ouvert) {
            throw new OperationImpossible("Le réseau social n'est pas ouvert.");
        }
        membres.add(membre);
    }

    public void ajouterModerateur(Moderateur moderateur) throws OperationImpossible {
    	ajouterMembre(moderateur);
        moderateurs.add(moderateur);
    }
	
    public void ajouterMessage(Message message) throws OperationImpossible {
        
        if (message.getMembre().getPseudoParticulier() == null || message.getMembre().getPseudoParticulier().isBlank()) {
            throw new OperationImpossible("Pseudo non valide.");
        }
    	if (!this.ouvert) {
            throw new OperationImpossible("Le réseau social n'est pas ouvert.");
        }
        messages.add(message);
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
