package dao;

import model.Equipamento;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {

    public void inserirEquipamento(Equipamento equipamento) {
        String sql = "INSERT INTO Equipamento (nomeEquipamento, tipo) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEquipamento(Equipamento equipamento) {
        String sql = "UPDATE Equipamento SET nomeEquipamento = ?, tipo = ? WHERE idEquipamento = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.setInt(3, equipamento.getIdEquipamento());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirEquipamento(int idEquipamento) {
        String sql = "DELETE FROM Equipamento WHERE idEquipamento = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipamento);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Equipamento> listarEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM Equipamento";

        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("idEquipamento"));
                equipamento.setNomeEquipamento(rs.getString("nomeEquipamento"));
                equipamento.setTipo(rs.getString("tipo"));

                equipamentos.add(equipamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }

    public List<Equipamento> listarEquipamentosPorProjeto(int idProjeto) {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT e.* FROM Equipamento e INNER JOIN Uso_Equipamento ue ON e.idEquipamento = ue.idEquipamento WHERE ue.idProjeto = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Equipamento equipamento = new Equipamento();
                equipamento.setIdEquipamento(rs.getInt("idEquipamento"));
                equipamento.setNomeEquipamento(rs.getString("nomeEquipamento"));
                equipamento.setTipo(rs.getString("tipo"));

                equipamentos.add(equipamento);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipamentos;
    }
}
