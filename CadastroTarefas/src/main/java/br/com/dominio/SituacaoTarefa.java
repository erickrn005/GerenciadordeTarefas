package br.com.dominio;

public enum SituacaoTarefa {
    EM_ANDAMENTO("EM_ANDAMENTO"),
    CONCLUIDA("CONCLUIDA");

    private String situacao;

    SituacaoTarefa(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public static SituacaoTarefa fromString(String str) {
        for (SituacaoTarefa situacao : SituacaoTarefa.values()) {
            if (situacao.situacao.equalsIgnoreCase(str)) {
                return situacao;
            }
        }
        return null;
    }
}
