package eu.telecomsudparis.csc4102.minisocs;


public enum EtatMessage {

	ACCEPTE("accepté"),

	VERIFICATION_PENDING("en attente"),
	
	HIDDEN("caché"),
	
	SENT("envoyé"),

	INAPPROPRIATE("inapproprié");

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
