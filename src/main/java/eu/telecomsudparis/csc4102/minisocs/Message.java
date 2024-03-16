package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;



public class Message {
	
	private String contenu;
	private EtatMessage etat;
	private ReseauSocial rs;
	private Membre membre;
	
	public Message(String contenu, Membre membre, EtatMessage etat, ReseauSocial rs) {
		if (contenu == null || contenu.isBlank()) {
			throw new IllegalArgumentException("contenu ne peut pas être null ou vide");
		}
		this.contenu = contenu;
		this.etat = etat;
		this.rs = rs;
		this.membre = membre;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(contenu);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
		return Objects.equals(contenu, other.contenu) && Objects.equals(rs, other.rs) && Objects.equals(membre, other.membre);
	}

	public Membre getMembre() {
		return membre;
	}
	
}