package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;

import eu.telecomsudparis.csc4102.util.OperationImpossible;



public class Membre {
	
	private String pseudoParticulier;
	private Utilisateur utilisateur;
	protected ReseauSocial rs;
	
	public Membre(final String pseudoParticulier, final Utilisateur utilisateur) {
		if (pseudoParticulier == null || pseudoParticulier.isBlank()) {
			throw new IllegalArgumentException("pseudoParticulier ne peut pas être null ou vide");
		}
		this.pseudoParticulier = pseudoParticulier;
		this.utilisateur = utilisateur;
	}
	
	public void changePseudoParticulier(String pseudoParticulier) {
		this.pseudoParticulier = pseudoParticulier;
	}
	
	public String getPseudoParticulier() {
		return pseudoParticulier;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
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

}
