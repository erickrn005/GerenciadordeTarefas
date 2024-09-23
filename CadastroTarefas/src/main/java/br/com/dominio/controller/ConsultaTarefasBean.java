package br.com.dominio.controller;

import br.com.dominio.Tarefa;
import br.com.dominio.controller.DAOcontroller.TarefaDAO;
import javax.faces.application.FacesMessage;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ConsultaTarefasBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<Tarefa> tarefas;
    private Tarefa tarefaSelecionada; // Para editar ou excluir uma tarefa!

    // Propriedades para buscar!
    private String idBusca;
    private String tituloBusca;
    private String responsavelBusca;
    private String situacaoBusca;
    private String descricaoBusca;

    // Método chamado após a construção do bean
    @PostConstruct
    public void init() {
        carregarTarefas(); // Carrega as tarefas!
        tarefaSelecionada = new Tarefa(); // Inicializa tarefaSelecionada!
    }

    public void carregarTarefas() {
        try {
            TarefaDAO tarefaDAO = new TarefaDAO(); 
            tarefas = tarefaDAO.obterTarefas(); 
            System.out.println("Total de tarefas carregadas: " + (tarefas != null ? tarefas.size() : 0));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao carregar tarefas.", e.getMessage()));
            e.printStackTrace(); 
        }
    }

    public List<Tarefa> getTarefas() {
        return tarefas != null ? tarefas : List.of(); // Retorna uma lista vazia se a tarefa for nulo!
    }

    public void buscarTarefas() {
        try {
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefas = tarefaDAO.buscarTarefas(idBusca, tituloBusca, responsavelBusca, situacaoBusca, descricaoBusca); // Adicionando descricaoBusca
            System.out.println("Tarefas buscadas: " + (tarefas != null ? tarefas.size() : 0));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar tarefas.", e.getMessage()));
            e.printStackTrace();
        }
    }

    public void buscarTarefaPorId() {
        try {
            // Define todos os outros parâmetros como nulos para focar apenas na busca pelo ID!
            tituloBusca = null;
            responsavelBusca = null;
            situacaoBusca = null;
            descricaoBusca = null;

            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefas = tarefaDAO.buscarTarefas(idBusca, tituloBusca, responsavelBusca, situacaoBusca, descricaoBusca);
            
            if (tarefas.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma tarefa encontrada com o ID informado.", null));
            } else {
                System.out.println("Tarefa buscada pelo ID: " + (tarefas != null ? tarefas.size() : 0));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar tarefa pelo ID.", e.getMessage()));
            e.printStackTrace();
        }
    }

    public void buscarTarefasEmAndamento() {
        try {
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefas = tarefaDAO.buscarTarefasEmAndamento(); // Busca tarefas em andamento!
            System.out.println("Tarefas em andamento buscadas: " + (tarefas != null ? tarefas.size() : 0));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao buscar tarefas em andamento.", e.getMessage()));
            e.printStackTrace();
        }
    }

    public void editarTarefa(Tarefa tarefa) {
        this.tarefaSelecionada = tarefa;
    }

    public void atualizarTarefa() {
        if (tarefaSelecionada != null) {
            try {
                TarefaDAO tarefaDAO = new TarefaDAO();
                tarefaDAO.atualizarTarefa(tarefaSelecionada); // Chama o método de atualização
                carregarTarefas(); // Recarrega a lista após a atualização
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa atualizada com sucesso!"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao atualizar tarefa.", e.getMessage()));
                e.printStackTrace();
            }
        }
    }

    public void excluirTarefa(Tarefa tarefa) {
        try {
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefaDAO.excluirTarefa(tarefa); // Implementar o método de exclusão em TarefaDAO
            carregarTarefas(); // Recarrega a lista após a exclusão
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa excluída com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao excluir tarefa.", e.getMessage()));
            e.printStackTrace();
        }
    }

    public void concluirTarefa(Tarefa tarefa) {
        try {
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefaDAO.concluirTarefa(tarefa); // Marcar a tarefa como concluída
            buscarTarefasEmAndamento(); // Recarrega apenas as tarefas em andamento
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Tarefa concluída com sucesso!"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao concluir tarefa.", e.getMessage()));
            e.printStackTrace();
        }
    }

    // Getters e Setters
    public Tarefa getTarefaSelecionada() {
        return tarefaSelecionada;
    }

    public void setTarefaSelecionada(Tarefa tarefaSelecionada) {
        this.tarefaSelecionada = tarefaSelecionada;
    }

    public String getIdBusca() {
        return idBusca;
    }

    public void setIdBusca(String idBusca) {
        this.idBusca = idBusca;
    }

    public String getTituloBusca() {
        return tituloBusca;
    }

    public void setTituloBusca(String tituloBusca) {
        this.tituloBusca = tituloBusca;
    }

    public String getResponsavelBusca() {
        return responsavelBusca;
    }

    public void setResponsavelBusca(String responsavelBusca) {
        this.responsavelBusca = responsavelBusca;
    }

    public String getSituacaoBusca() {
        return situacaoBusca;
    }

    public void setSituacaoBusca(String situacaoBusca) {
        this.situacaoBusca = situacaoBusca;
    }

    public String getDescricaoBusca() {
        return descricaoBusca; // Novo getter
    }

    public void setDescricaoBusca(String descricaoBusca) {
        this.descricaoBusca = descricaoBusca; // Novo setter
    }
}
