package eu.telecomsudparis.csc4102.minisocs;


public enum EtatMessage {

	VERIFICATION_PENDING("en attente"),
	
	HIDDEN("caché"),
	
	SENT("envoyé"),

	INAPPROPRIATE("inapproprié"),
	
	DELETED("supprimé");

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
