package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

/**
 * Cette classe represente les messages.
 * 
 * @author Alexandre Chidiac et Luka Lafaye de Micheaux
 */
public class Message {
	/**
	 * id initial.
	 */
	private static double idcpt = 0;
	/**
	 * le contenu du message.
	 */
	private String contenu;
	/**
	 * l etat du message.
	 */
	private EtatMessage etat;
	/**
	 * le reseau social.
	 */
	private ReseauSocial rs;
	/**
	 * le membre.
	 */
	private Membre membre;
	/**
	 * l id du message.
	 */
	private final double id;
	
	/**
	 * construit une instance de la classe message.
	 * 
	 * @param contenu le contenu du message.
	 * @param membre le membre qui publie le message.
	 * @param etat l'etat du message.
	 * @param rs le reseau social.
	 */
	public Message(final String contenu, final Membre membre, final EtatMessage etat, final ReseauSocial rs) {
		if (membre == null) {
			throw new IllegalArgumentException("membre ne peut pas être null");
		}
		
        if (rs == null) {
        	throw new IllegalArgumentException("RS null");
        }
        
        if (etat == null) {
        	throw new IllegalArgumentException("etat null");
        }
        
		if (contenu == null || contenu.isBlank()) {
			throw new IllegalArgumentException("contenu ne peut pas être null ou vide");
		}
		
		if (membre.getPseudoParticulier() == null || membre.getPseudoParticulier().isBlank()) {
			throw new IllegalArgumentException("membre pseudo null/blank");
		}
        
		this.contenu = contenu;
		this.etat = etat;
		this.rs = rs;
		this.membre = membre;
		this.id = idcpt++;
	    assert invariant();
	}
	
	/**
	 * getter pour le id.
	 * @return le id du message
	 */
    public double getId() {
        return this.id;
    }

	/**
	 * methode hashCode.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * methode equals. 
	 */
	@Override
	public boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
	    return other.hashCode() == this.hashCode();
	}

	/**
	 * methode toString.
	 */
	@Override
	public String toString() {
		return "On social : " + this.rs + "\nFrom : " + this.membre + "\nMessage : " + this.contenu + "\nStatus : " + this.etat;
	}
	
	/**
	 * getter pour le membre.
	 * @return le membre
	 */
	public Membre getMembre() {
		return membre;
	}
	
	/**
	 * modifie l etat du message.
	 * 
	 * @param etat l etat du message desire
	 * 
	 */
	public void modifierStatutMessage(final EtatMessage etat) {
		if (this.membre == null) {
			throw new IllegalArgumentException("membre ne peut pas être null");
		}
		
        if (this.rs == null) {
        	throw new IllegalArgumentException("RS null");
        }
        
		if (this.contenu == null || contenu.isBlank()) {
			throw new IllegalArgumentException("contenu ne peut pas être null ou vide");
		}
		
		if (this.membre.getPseudoParticulier() == null || membre.getPseudoParticulier().isBlank()) {
			throw new IllegalArgumentException("membre pseudo null/blank");
		}
		
		if (etat == null) {
        	throw new IllegalArgumentException("etat null");
        }

	    this.etat = etat;
	    assert invariant();
	}
	
	
	/**
	 * accepte le message si celui qui le poste est moderateur. 
	 * 
	 * 
	 */
	public void envoyerMessage() {
		if (membre == null) {
			throw new IllegalArgumentException("membre ne peut pas être null");
		}
		
        if (rs == null) {
        	throw new IllegalArgumentException("RS null");
        }
        
		if (contenu == null || contenu.isBlank()) {
			throw new IllegalArgumentException("contenu ne peut pas être null ou vide");
		}
		
		if (membre.getPseudoParticulier() == null || membre.getPseudoParticulier().isBlank()) {
			throw new IllegalArgumentException("membre pseudo null/blank");
		}
		
	    if (membre instanceof Moderateur) {
	        this.etat = EtatMessage.ACCEPTE;
	    }
	    
	    assert invariant();
	}
	
	/**
	 * invariant.
	 * @return true si l'invariant est respecte
	 */
    public boolean invariant() {
        if (contenu == null || contenu.isBlank()) {
            throw new IllegalStateException("Invariant violation: contenu ne peut pas être null ou vide");
        }

        if (!(etat == EtatMessage.ACCEPTE || etat == EtatMessage.VERIFICATION_PENDING 
        		||
              etat == EtatMessage.INAPPROPRIATE || etat == EtatMessage.HIDDEN 
              ||
              etat == EtatMessage.SENT)) {
            throw new IllegalStateException("Invariant violation: état du message invalide");
        }

        Objects.requireNonNull(membre, "Invariant violation: membre ne peut pas être null");
        Objects.requireNonNull(rs, "Invariant violation: reseauSocial ne peut pas être null");
        
        return true;
    }

	/**
	 * getter du contenu.
	 * @return contenu
	 */
	public String getContenu() {
		return this.contenu;
	}
	/**
	 * getter de l etat du message.
	 * @return l'etat du message
	 */
	public EtatMessage getEtat() {
		return this.etat;
	}
	/**
	 * getter du reseau social.
	 * @return le reseau social
	 */
	public ReseauSocial getReseauSocial() {
		return this.rs;
	}
}
