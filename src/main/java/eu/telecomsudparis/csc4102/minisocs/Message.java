package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

public class Message {
	private static double id_cpt = 0;
	private String contenu;
	EtatMessage etat;
	private ReseauSocial rs;
	private Membre membre;
	private final double id;
	
	public Message(String contenu, Membre membre, EtatMessage etat, ReseauSocial rs) {
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
        
		this.contenu = contenu;
		this.etat = etat;
		this.rs = rs;
		this.membre = membre;
		this.id = id_cpt++;
	    assert invariant();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
	    return obj.hashCode() == this.hashCode();
	}

	@Override
	public String toString() {
		return "On social : " + this.rs + "\nFrom : " + this.membre + "\nMessage : " + this.contenu + "\nStatus : " + this.etat;
	}
	
	public Membre getMembre() {
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
	    assert invariant();
		return membre;
	}
	
	public void delete_message() {
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
		
		//
	}
	
	
	public void hide_message() {
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
	        // Perform action for admin role
	        this.etat = EtatMessage.HIDDEN;
	    } else {
	        throw new UnsupportedOperationException("This operation is not supported for the current role.");
	    }
	    
	    assert invariant();
	}
	
	
	public void mark_inappropriate() {
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
	        // Perform action for admin role
	        this.etat = EtatMessage.INAPPROPRIATE;
	    } else {
	        throw new UnsupportedOperationException("This operation is not supported for the current role.");
	    }

	    assert invariant();
	}
	
	
	public void send_message() {
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
	        // Perform action for admin role
	        System.out.println("Sending message as Moderator...");
	        this.etat = EtatMessage.ACCEPTE;
	    } else {
	        this.etat = EtatMessage.VERIFICATION_PENDING;
	    }
	    
	    assert invariant();
	}
	
    public boolean invariant() {
        if (contenu == null || contenu.isBlank()) {
            throw new IllegalStateException("Invariant violation: contenu ne peut pas être null ou vide");
        }

        if (!(etat == EtatMessage.ACCEPTE || etat == EtatMessage.VERIFICATION_PENDING ||
              etat == EtatMessage.INAPPROPRIATE || etat == EtatMessage.HIDDEN ||
              etat == EtatMessage.SENT)) {
            throw new IllegalStateException("Invariant violation: état du message invalide");
        }

        Objects.requireNonNull(membre, "Invariant violation: membre ne peut pas être null");
        Objects.requireNonNull(rs, "Invariant violation: reseauSocial ne peut pas être null");
        
        return true;
    }

	public String getContenu() {
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
		
	    assert invariant();
		return contenu;
	}

	public EtatMessage getEtat() {
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
		
	    assert invariant();
		return etat;
	}

	public ReseauSocial getReseauSocial() {
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
		
	    assert invariant();
		return rs;
	}
}