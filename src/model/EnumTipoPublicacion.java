package model;

public enum EnumTipoPublicacion {
    IMAGEN("Imagen"),
    VIDEO("Video"),
    AUDIO("Audio");

    private final String displayName;

    private EnumTipoPublicacion(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
