package dao;

import model.Operario;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO {

    public void inserirOperario(Operario operario) {
        String sql = "INSERT INTO Operario (nomeOperario, funcao) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarOperario(Operario operario) {
        String sql = "UPDATE Operario SET nomeOperario = ?, funcao = ? WHERE idOperario = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.setInt(3, operario.getIdOperario());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirOperario(int idOperario) {
        String sql = "DELETE FROM Operario WHERE idOperario = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idOperario);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Operario> listarOperarios() {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT * FROM Operario";

        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("idOperario"));
                operario.setNomeOperario(rs.getString("nomeOperario"));
                operario.setFuncao(rs.getString("funcao"));

                operarios.add(operario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operarios;
    }

    public List<Operario> listarOperariosPorProjeto(int idProjeto) {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT o.* FROM Operario o INNER JOIN Alocacao_Operario ao ON o.idOperario = ao.idOperario WHERE ao.idProjeto = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Operario operario = new Operario();
                operario.setIdOperario(rs.getInt("idOperario"));
                operario.setNomeOperario(rs.getString("nomeOperario"));
                operario.setFuncao(rs.getString("funcao"));

                operarios.add(operario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return operarios;
    }
}
