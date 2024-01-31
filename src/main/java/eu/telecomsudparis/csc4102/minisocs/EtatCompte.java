package eu.telecomsudparis.csc4102.minisocs;

/**
 * Ce type énuméré modélise l'état du compte d'un utilisateur.
 */
public enum EtatCompte {
	/**
	 * le compte de l'utilisateur est actif.
	 */
	ACTIF("actif"),
	/**
	 * le compte de l'utilisateur a été désactivé par l'utilisateur.
	 */
	DESACTIVE("désactivé"),
	/**
	 * le compte de l'utilisateur est bloqué par le système.
	 */
	BLOQUE("bloqué");

	/**
	 * le nom de l'état à afficher.
	 */
	private String nom;

	/**
	 * construit un énumérateur.
	 * 
	 * @param nom le nom de l'état.
	 */
	EtatCompte(final String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return nom;
	}
}
