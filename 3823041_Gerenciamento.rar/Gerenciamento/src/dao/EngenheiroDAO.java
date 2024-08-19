package dao;

import model.Engenheiro;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO {

    public void inserirEngenheiro(Engenheiro engenheiro) {
        String sql = "INSERT INTO Engenheiro (nomeEngenheiro, especialidade) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEngenheiro(Engenheiro engenheiro) {
        String sql = "UPDATE Engenheiro SET nomeEngenheiro = ?, especialidade = ? WHERE idEngenheiro = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.setInt(3, engenheiro.getIdEngenheiro());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirEngenheiro(int idEngenheiro) {
        String sql = "DELETE FROM Engenheiro WHERE idEngenheiro = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEngenheiro);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Engenheiro> listarEngenheiros() {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT * FROM Engenheiro";

        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("idEngenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("nomeEngenheiro"));
                engenheiro.setEspecialidade(rs.getString("especialidade"));

                engenheiros.add(engenheiro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engenheiros;
    }

    public List<Engenheiro> listarEngenheirosPorProjeto(int idProjeto) {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT e.* FROM Engenheiro e INNER JOIN Alocacao_Engenheiro ae ON e.idEngenheiro = ae.idEngenheiro WHERE ae.idProjeto = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro();
                engenheiro.setIdEngenheiro(rs.getInt("idEngenheiro"));
                engenheiro.setNomeEngenheiro(rs.getString("nomeEngenheiro"));
                engenheiro.setEspecialidade(rs.getString("especialidade"));

                engenheiros.add(engenheiro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return engenheiros;
    }
}
