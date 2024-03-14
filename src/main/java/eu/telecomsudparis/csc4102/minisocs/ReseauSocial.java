package eu.telecomsudparis.csc4102.minisocs;

import java.util.Objects;



public class ReseauSocial {
	/**
	 * le nom du RS.
	 */
	private String nom;
	
	public ReseauSocial(final String nom) {
		if (nom == null || nom.isBlank()) {
			throw new IllegalArgumentException("nom ne peut pas être null ou vide");
		}
		this.nom = nom;
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
