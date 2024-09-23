package br.com.dominio;

public enum ResponsavelTarefa {
    ERICK("Erick"),
    JOAO("Joao");

    private String responsavel;

    ResponsavelTarefa(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getResponsavel() {
        return responsavel;
    }
    
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    // Método para obter ResponsavelTarefa a partir de uma string
    public static ResponsavelTarefa fromString(String responsavelStr) {
        if (responsavelStr != null) {
            for (ResponsavelTarefa responsavel : ResponsavelTarefa.values()) {
                if (responsavel.getResponsavel().equalsIgnoreCase(responsavelStr)) {
                    return responsavel;
                }
            }
        }
        return null;
    }
}
