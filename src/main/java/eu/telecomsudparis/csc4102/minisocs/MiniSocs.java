package eu.telecomsudparis.csc4102.minisocs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;

import org.apache.commons.validator.routines.EmailValidator;

import eu.telecomsudparis.csc4102.util.OperationImpossible;

/**
 * Cette classe est la façade du logiciel.
 * 
 * @author Denis Conan
 */
public class MiniSocs {
	/**
	 * le nom du système.
	 */
	private final String nomDuSysteme;
	/**
	 * les utilisateurs.
	 */
	private Map<String, Utilisateur> utilisateurs;
	/**
	 * les reseaux sociaux.
	 */
	private Map<String, ReseauSocial> reseauxSociaux;

	/**
	 * construit une instance du système.
	 * 
	 * @param nomDuSysteme le nom.
	 */
	public MiniSocs(final String nomDuSysteme) {
		this.nomDuSysteme = nomDuSysteme;
		this.utilisateurs = new HashMap<>();
		this.reseauxSociaux = new HashMap<>();
		
		assert invariant();
	}

	/**
	 * ajoute un utilisateur.
	 * 
	 * @param pseudo   le pseudo de l'utilisateur.
	 * @param nom      le nom de l'utilisateur.
	 * @param prenom   le prénom de l'utilisateur.
	 * @param courriel le courriel de l'utilisateur.
	 * @throws OperationImpossible en cas de problème sur les pré-conditions.
	 */
	public Utilisateur ajouterUtilisateur(final String pseudo, final String nom, final String prenom, final String courriel)
			throws OperationImpossible {
		if (pseudo == null || pseudo.isBlank()) {
			throw new OperationImpossible("pseudo ne peut pas être null ou vide");
		}
		if (nom == null || nom.isBlank()) {
			throw new OperationImpossible("nom ne peut pas être null ou vide");
		}
		if (prenom == null || prenom.isBlank()) {
			throw new OperationImpossible("prenom ne peut pas être null ou vide");
		}
		if (courriel == null || courriel.isBlank()) {
			throw new OperationImpossible("courriel ne peut pas être null ou vide");
		}
		if (!EmailValidator.getInstance().isValid(courriel)) {
			throw new OperationImpossible("courriel ne respecte pas le standard RFC822");
		}
		Utilisateur u = utilisateurs.get(pseudo);
		if (u != null) {
			throw new OperationImpossible(pseudo + "déjà un utilisateur");
		}
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, courriel);
		utilisateurs.put(pseudo, user);
		
		assert invariant();
		return user;
	}
	
	/**
	 * cree un reseau social.
	 * 
	 * @param pseudoExec   le pseudo de l'utilisateur.
	 * @param nomReseau      le nom du reseau social.
	 * @param ouvert   l etat initial du RS.
	 * @param pseudoParticulier le pseudo du membre particulier au reseau social.
	 * @throws IllegalArgumentException en cas de problème sur les pré-conditions.
	 */
    public ReseauSocial creerReseauSocial(final String pseudoExec, final String nomReseau, final boolean ouvert, final String pseudoParticulier) throws OperationImpossible {
        if (pseudoExec == null || pseudoExec.isBlank()) {
        	throw new OperationImpossible("Pseudo de l'executeur non valide");
        }
        Utilisateur u = utilisateurs.get(pseudoExec);
        if (u == null) {
        	throw new OperationImpossible("Le compte de l'exécuteur n'existe pas");
        }
        if (u.getEtatCompte() != EtatCompte.ACTIF) {
            throw new OperationImpossible("Le compte de l'exécuteur n'est pas actif ou est bloqué.");
        }
        if (nomReseau == null || nomReseau.isBlank()) {
            throw new OperationImpossible("Nom du réseau social non valide.");
        }
        
        if (pseudoParticulier == null || pseudoParticulier.isBlank()) {
            throw new OperationImpossible("pseudoParticulier du réseau social non valide.");
        }
        
		ReseauSocial rs = reseauxSociaux.get(nomReseau);
		if (rs != null) {
			throw new OperationImpossible("Nom deja pris");
		}

        ReseauSocial nouveauReseau = new ReseauSocial(nomReseau, ouvert);
        reseauxSociaux.put(nouveauReseau.getNom(), nouveauReseau);      
        
        nouveauReseau.setOuvert(true);
        Membre m = ajouterMembreRS(pseudoExec, nomReseau, pseudoParticulier, true);
        nouveauReseau.setOuvert(ouvert);
          
	    assert invariant();
	    return nouveauReseau;
    }
	
	/**
	 * ajoute un membre a un reseau social.
	 * 
	 * @param pseudo   le pseudo de l'utilisateur.
	 * @param nomReseau      le nom du reseau social.
	 * @param mod   boolean indiquant si le membre est moderateur.
	 * @param pseudoParticulier le pseudo du membre particulier au reseau social.
	 * @throws OperationImpossible en cas de problème sur les pré-conditions.
	 */
    public Membre ajouterMembreRS(final String pseudo, final String nomReseau, final String pseudoParticulier, final boolean mod) throws OperationImpossible {
        if (pseudo == null || pseudo.isBlank()) {
        	throw new OperationImpossible("Pseudo de l'executeur non valide");
        }
        if (nomReseau == null || nomReseau.isBlank()) {
        	throw new OperationImpossible("Nom du Reseau non valide");
        }
        Utilisateur u = utilisateurs.get(pseudo);
        
        ReseauSocial rs = reseauxSociaux.get(nomReseau);
        
        if (rs == null) {
        	throw new OperationImpossible("Le réseau social " + nomReseau + " n'existe pas.");
        }
        if (u == null || u.getEtatCompte() != EtatCompte.ACTIF) {
            throw new OperationImpossible("L'utilisateur n'est pas actif ou n'existe pas.");
        }
        if (!rs.getOuvert()) {
            throw new OperationImpossible("Le réseau social n'est pas ouvert.");
        }
        
        Membre m = rs.getMembrefromUtilisateur(u);
        
        if (m != null) {
        	// un membre est déjà créé pour cet utilisateur sur le rs
        	throw new OperationImpossible("membre est déjà créé pour cet utilisateur sur le rs");
        } else {
        	m = rs.ajouterMembre(u, pseudoParticulier, mod);
        }
        
        assert invariant();
        return m;
    }
	
	/**
	 * post un message.
	 * 
	 * @param pseudo   le pseudo de l'utilisateur.
	 * @param nomReseau      le nom du reseau social.
	 * @param contenu   le contenu du message.
	 * @throws OperationImpossible en cas de problème sur les pré-conditions.
	 */
    public Message posterMessageRS(final String pseudo, final String contenu, final String nomReseau) throws OperationImpossible {
        if (pseudo == null || pseudo.isBlank()) {
        	throw new OperationImpossible("Pseudo de l'executeur non valide");
        }
        if (nomReseau == null || nomReseau.isBlank()) {
        	throw new OperationImpossible("Nom du Reseau non valide");
        }
        Utilisateur u = utilisateurs.get(pseudo);
        ReseauSocial rs = reseauxSociaux.get(nomReseau);
        if (contenu == null || contenu.isBlank()) {
            throw new OperationImpossible("Le contenu du message ne peut pas être null ou vide.");
        }
        if (u == null || u.getEtatCompte() != EtatCompte.ACTIF) {
            throw new OperationImpossible("L'utilisateur n'est pas actif ou n'existe pas.");
        }
        
        if (rs == null) {
            throw new OperationImpossible("Le réseau social n'existe pas.");
        }
        
    	if (!rs.getOuvert()) {
            throw new OperationImpossible("Le réseau social n'est pas ouvert.");
        }
    	Membre m = rs.getMembrefromUtilisateur(u);
		if (m == null) {
			throw new OperationImpossible("L'utilisateur n'est pas membre du Reseau");
		}
        if (m.getPseudoParticulier() == null || m.getPseudoParticulier().isBlank()) {
        	throw new OperationImpossible("Pseudo de l'executeur non valide");
        }
        Message msg = rs.ajouterMessage(contenu, m);
        
        assert invariant();
        
        return msg;
    }
    
	/**
	 * modere un message.
	 * 
	 * @param pseudo   le pseudo de l'utilisateur.
	 * @param nomReseau      le nom du reseau social.
	 * @param id   le id du message.
	 * @param etat l etat du message attendu apres moderation.
	 * @throws OperationImpossible en cas de problème sur les pré-conditions.
	 */
    public void modererMessage(final String pseudo, final double id, final String nomReseau, final EtatMessage etat) throws OperationImpossible {
        if (pseudo == null || pseudo.isBlank()) {
        	throw new OperationImpossible("Pseudo de l'executeur non valide");
        }
        
        if (id < 0) {
        	throw new OperationImpossible("Id doit être positif");
        }
        
        if (nomReseau == null || nomReseau.isBlank()) {
        	throw new OperationImpossible("Nom du Reseau non valide");
        }
        Utilisateur u = utilisateurs.get(pseudo);
        ReseauSocial rs = reseauxSociaux.get(nomReseau);
        if (u == null || u.getEtatCompte() != EtatCompte.ACTIF) {
            throw new OperationImpossible("L'utilisateur n'est pas actif ou n'existe pas.");
        }
        if (rs == null) {
        	throw new OperationImpossible("Le réseau social n'existe pas.");
        }
        
    	if (!rs.getOuvert()) {
            throw new OperationImpossible("Le réseau social n'est pas ouvert.");
        }
    	Membre m = rs.getMembrefromUtilisateur(u);
		if (m == null) {
			throw new OperationImpossible("L'utilisateur n'est pas membre du Reseau");
		}
        if (m.getPseudoParticulier() == null || m.getPseudoParticulier().isBlank()) {
        	throw new OperationImpossible("Pseudo de l'executeur non valide");
        }
        Message message = rs.getMessageFromId(id);
		if (message == null) {
			throw new OperationImpossible("Le message n'appartient pas au reseau ou n'existe pas");
		}
        m.moderer(message, etat);
        assert invariant();
    }
	/**
	 * liste les utilisateurs.
	 * 
	 * @return la liste des pseudonymes des utilisateurs.
	 */
	public List<String> listerUtilisateurs() {
		List<String> listeUtilisateurs = new ArrayList<>();
        for (Utilisateur u : utilisateurs.values()) {
        	listeUtilisateurs.add(u.getPseudonyme());
        }
        return listeUtilisateurs;
	}
	/**
	 * liste les reseaux sociaux.
	 * 
	 * @return la liste des noms des reseaux sociaux.
	 */
	public List<String> listerRS() {
        List<String> nomsReseaux = new ArrayList<>();
        for (ReseauSocial rs : reseauxSociaux.values()) {
            nomsReseaux.add(rs.getNom());
        }
        return nomsReseaux;
    }

	/**
	 * désactiver son compte utilisateur.
	 * 
	 * @param pseudo le pseudo de l'utilisateur.
	 * @throws OperationImpossible en cas de problèmes sur les pré-conditions.
	 */
	public void desactiverCompteUtilisateur(final String pseudo) throws OperationImpossible {
		if (pseudo == null || pseudo.isBlank()) {
			throw new OperationImpossible("pseudo ne peut pas être null ou vide");
		}
		Utilisateur u = utilisateurs.get(pseudo);
		if (u == null) {
			throw new OperationImpossible("utilisateur inexistant avec ce pseudo (" + pseudo + ")");
		}
		if (u.getEtatCompte().equals(EtatCompte.BLOQUE)) {
			throw new OperationImpossible("le compte est bloqué");
		}
		u.desactiverCompte();
	    assert invariant();
	}
	
	/**
	 * invariant de classe.
	 * @return true si invariant est respecte
	 */
    public boolean invariant() {
        if (nomDuSysteme == null || nomDuSysteme.isBlank()) {
        	throw new IllegalStateException("Invariant violation: contenu ne peut pas être null ou vide");
        }

        Objects.requireNonNull(utilisateurs, "Invariant violation: membre ne peut pas être null");
        Objects.requireNonNull(reseauxSociaux, "Invariant violation: reseauSocial ne peut pas être null");
        
        return true;
    }

	/**
	 * obtient le nom du projet.
	 * 
	 * @return le nom du projet.
	 */
	public String getNomDeProjet() {
		return nomDuSysteme;
	}
	
	public Map<String, ReseauSocial> getReseauxSociaux() {
	    return reseauxSociaux;
	}

	/**
	 * toString.
	 * @return le string correspondant.
	 */
	@Override
	public String toString() {
		return "MiniSocs [nomDuSysteme=" + nomDuSysteme + ", utilisateurs=" + utilisateurs + "]";
	}
}