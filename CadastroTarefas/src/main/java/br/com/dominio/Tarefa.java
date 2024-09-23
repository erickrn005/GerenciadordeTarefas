package br.com.dominio;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Tarefa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ResponsavelTarefa responsavel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SituacaoTarefa situacao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PrioridadeTarefa prioridade;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date deadline;

    public Tarefa() {
    }

    // Construtor com parâmetros
    public Tarefa(String titulo, String descricao, ResponsavelTarefa responsavel, SituacaoTarefa situacao,
                  PrioridadeTarefa prioridade, Date deadline) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.responsavel = responsavel;
        this.situacao = situacao;
        this.prioridade = prioridade;
        this.deadline = deadline;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public ResponsavelTarefa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(ResponsavelTarefa responsavel) {
        this.responsavel = responsavel;
    }

    public SituacaoTarefa getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoTarefa situacao) {
        this.situacao = situacao;
    }

    public PrioridadeTarefa getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeTarefa prioridade) {
        this.prioridade = prioridade;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarefa other = (Tarefa) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Tarefa [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao +
               ", responsavel=" + responsavel + ", situacao=" + situacao + ", prioridade=" + prioridade +
               ", deadline=" + deadline + "]"; 
    }
}
