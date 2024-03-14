package eu.telecomsudparis.csc4102.minisocs;


public enum EtatMessage {

	CACHE("caché"),

	VISIBLE("visible"),

	MODERE("modéré");

	/**
	 * le nom de l'état à afficher.
	 */
	private String nom;

	/**
	 * construit un énumérateur.
	 * 
	 * @param nom le nom de l'état.
	 */
	EtatMessage(final String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
}
