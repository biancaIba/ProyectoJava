package modelo.enums;

/**
 * El Enum EnumTipoPublicacion.
 */
public enum EnumTipoPublicacion {

	/** Publicacion Imagen */
	IMAGEN("Imagen"),

	/** Publicacion Video */
	VIDEO("Video"),

	/** Publicacion Audio */
	AUDIO("Audio");

	/** El display name. */
	private final String displayName;

	/**
	 * Instancia un nuevo enum tipo publicacion.
	 *
	 * @param displayName
	 */
	private EnumTipoPublicacion(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Obtiene el display name.
	 *
	 * @return el display name
	 */
	public String getDisplayName() {
		return displayName;
	}
}
