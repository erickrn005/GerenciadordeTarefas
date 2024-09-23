package br.com.dominio;

public enum PrioridadeTarefa {
    
    BAIXA("Baixa"),
    MEDIA("Média"),
    ALTA("Alta");

    private String prioridade;

    PrioridadeTarefa(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    // Método para obter PrioridadeTarefa a partir de uma string
    public static PrioridadeTarefa fromString(String prioridadeStr) {
        if (prioridadeStr != null) {
            for (PrioridadeTarefa prioridade : PrioridadeTarefa.values()) {
                if (prioridade.getPrioridade().equalsIgnoreCase(prioridadeStr)) {
                    return prioridade;
                }
            }
        }
        return null;
    }
}
