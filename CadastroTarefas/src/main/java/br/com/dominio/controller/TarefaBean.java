package br.com.dominio.controller;


import java.util.Date;
import javax.faces.view.ViewScoped;
import javax.inject.Named; 
import br.com.dominio.PrioridadeTarefa;
import br.com.dominio.ResponsavelTarefa;
import br.com.dominio.Tarefa;
import br.com.dominio.controller.DAOcontroller.TarefaDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named  // CDI = ManagedBean
@ViewScoped  // CDI
public class TarefaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String descricao;
    private ResponsavelTarefa responsavel; // Mantendo como objeto do enum
    private PrioridadeTarefa prioridade; // Mantendo como objeto do enum
    private Date deadline;
    
    private List<Tarefa> tarefas = new ArrayList<>();

    public TarefaBean() {
    }

    // Getters e setters
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

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void cadastrarTarefa() {
        TarefaDAO tarefaDAO = new TarefaDAO();
        String responsavelString = responsavel.name(); // Obtém o nome do enum
        String prioridadeString = prioridade.name(); // Obtém o nome do enum

        // Chama o método do DAO
        tarefaDAO.cadastrarTarefa(responsavelString, descricao, titulo, deadline, prioridadeString);

        // Limpa os campos após o cadastro
        limparCampos();

        // Mensagem de sucesso
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa cadastrada com sucesso!"));
    }

    public String consultarTarefas() {
        return "ConsultarTarefas";
    }

    private void limparCampos() {
        titulo = null;
        descricao = null;
        responsavel = null;
        prioridade = null;
        deadline = null;
    }
}
