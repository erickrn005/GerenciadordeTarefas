package br.com.dominio.controller.DAOcontroller;

import br.com.dominio.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarefaDAO {

    public void cadastrarTarefa(String responsavel, String descricao, String titulo, Date deadline, String prioridade) {
        String sql = "INSERT INTO tarefa (titulo, descricao, responsavel, deadline, prioridade, situacao) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Bancoconector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            pstmt.setString(2, descricao);
            pstmt.setString(3, responsavel);
            pstmt.setTimestamp(4, new Timestamp(deadline.getTime()));
            pstmt.setString(5, prioridade);
            pstmt.setString(6, SituacaoTarefa.EM_ANDAMENTO.getSituacao());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Tarefa> obterTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa";

        try (Connection conn = Bancoconector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getLong("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                System.out.println("Descrição recuperada: " + tarefa.getDescricao());

                String responsavelStr = rs.getString("responsavel");
                tarefa.setResponsavel(ResponsavelTarefa.fromString(responsavelStr));
                tarefa.setDeadline(rs.getTimestamp("deadline"));

                String prioridadeStr = rs.getString("prioridade");
                tarefa.setPrioridade(PrioridadeTarefa.fromString(prioridadeStr));

                String situacaoStr = rs.getString("situacao");
                tarefa.setSituacao(SituacaoTarefa.fromString(situacaoStr));

                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public boolean excluirTarefa(Tarefa tarefa) {
        String sql = "DELETE FROM tarefa WHERE id = ?";

        try (Connection conn = Bancoconector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, tarefa.getId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void concluirTarefa(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET situacao = ? WHERE id = ?";

        try (Connection conn = Bancoconector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, SituacaoTarefa.CONCLUIDA.getSituacao());
            pstmt.setLong(2, tarefa.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar tarefas
    public List<Tarefa> buscarTarefas(String id, String titulo, String responsavel, String situacao, String descricao) {
        List<Tarefa> tarefas = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM tarefa WHERE 1=1");

        if (id != null && !id.isEmpty()) {
            sql.append(" AND id = ?");
        }
        if (titulo != null && !titulo.isEmpty()) {
            sql.append(" AND titulo LIKE ?");
        }
        if (responsavel != null && !responsavel.isEmpty()) {
            sql.append(" AND responsavel = ?");
        }
        if (situacao != null && !situacao.isEmpty()) {
            sql.append(" AND situacao = ?");
        }
        if (descricao != null && !descricao.isEmpty()) {
            sql.append(" AND descricao LIKE ?"); // Adiciona a condição para a descrição
        }

        try (Connection conn = Bancoconector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            int index = 1;

            if (id != null && !id.isEmpty()) {
                pstmt.setLong(index++, Long.parseLong(id));
            }
            if (titulo != null && !titulo.isEmpty()) {
                pstmt.setString(index++, "%" + titulo + "%");
            }
            if (responsavel != null && !responsavel.isEmpty()) {
                pstmt.setString(index++, responsavel);
            }
            if (situacao != null && !situacao.isEmpty()) {
                pstmt.setString(index++, situacao);
            }
            if (descricao != null && !descricao.isEmpty()) {
                pstmt.setString(index++, "%" + descricao + "%"); // Adiciona o parâmetro de descrição
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setId(rs.getLong("id"));
                    tarefa.setTitulo(rs.getString("titulo"));
                    tarefa.setDescricao(rs.getString("descricao"));

                    String responsavelStr = rs.getString("responsavel");
                    tarefa.setResponsavel(ResponsavelTarefa.fromString(responsavelStr));

                    tarefa.setDeadline(rs.getTimestamp("deadline"));

                    String prioridadeStr = rs.getString("prioridade");
                    tarefa.setPrioridade(PrioridadeTarefa.fromString(prioridadeStr));

                    String situacaoStr = rs.getString("situacao");
                    tarefa.setSituacao(SituacaoTarefa.fromString(situacaoStr));

                    tarefas.add(tarefa);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public List<Tarefa> buscarTarefasEmAndamento() {
        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE situacao != 'CONCLUIDA'";

        try (Connection conn = Bancoconector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getLong("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));

                String responsavelStr = rs.getString("responsavel");
                tarefa.setResponsavel(ResponsavelTarefa.fromString(responsavelStr));

                tarefa.setDeadline(rs.getTimestamp("deadline"));

                String prioridadeStr = rs.getString("prioridade");
                tarefa.setPrioridade(PrioridadeTarefa.fromString(prioridadeStr));

                String situacaoStr = rs.getString("situacao");
                tarefa.setSituacao(SituacaoTarefa.fromString(situacaoStr));

                tarefas.add(tarefa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarefas;
    }

    public void atualizarTarefa(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET titulo = ?, descricao = ?, responsavel = ?, deadline = ?, prioridade = ?, situacao = ? WHERE id = ?";

        try (Connection conn = Bancoconector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tarefa.getTitulo());
            pstmt.setString(2, tarefa.getDescricao());
            pstmt.setString(3, tarefa.getResponsavel().toString());
            pstmt.setTimestamp(4, new Timestamp(tarefa.getDeadline().getTime()));
            pstmt.setString(5, tarefa.getPrioridade().toString());
            pstmt.setString(6, tarefa.getSituacao().getSituacao());
            pstmt.setLong(7, tarefa.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
