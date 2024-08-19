package dao;

import model.Projeto;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public void inserirProjeto(Projeto projeto) {
        String sql = "INSERT INTO Projeto (nomeProjeto, local, dataInicio, dataTermino) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(projeto.getDataTermino().getTime()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProjeto(Projeto projeto) {
        String sql = "UPDATE Projeto SET nomeProjeto = ?, local = ?, dataInicio = ?, dataTermino = ? WHERE idProjeto = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setDate(3, new java.sql.Date(projeto.getDataInicio().getTime()));
            stmt.setDate(4, new java.sql.Date(projeto.getDataTermino().getTime()));
            stmt.setInt(5, projeto.getIdProjeto());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProjeto(int idProjeto) {
        String sql = "DELETE FROM Projeto WHERE idProjeto = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Projeto> listarProjetos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto";

        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("idProjeto"));
                projeto.setNomeProjeto(rs.getString("nomeProjeto"));
                projeto.setLocal(rs.getString("local"));
                projeto.setDataInicio(rs.getDate("dataInicio"));
                projeto.setDataTermino(rs.getDate("dataTermino"));

                projetos.add(projeto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }

    public List<Projeto> listarProjetosPorEngenheiro(int idEngenheiro) {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT p.* FROM Projeto p INNER JOIN Alocacao_Engenheiro ae ON p.idProjeto = ae.idProjeto WHERE ae.idEngenheiro = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEngenheiro);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("idProjeto"));
                projeto.setNomeProjeto(rs.getString("nomeProjeto"));
                projeto.setLocal(rs.getString("local"));
                projeto.setDataInicio(rs.getDate("dataInicio"));
                projeto.setDataTermino(rs.getDate("dataTermino"));

                projetos.add(projeto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }

    public List<Projeto> listarProjetosPorOperario(int idOperario) {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT p.* FROM Projeto p INNER JOIN Alocacao_Operario ao ON p.idProjeto = ao.idProjeto WHERE ao.idOperario = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idOperario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setIdProjeto(rs.getInt("idProjeto"));
                projeto.setNomeProjeto(rs.getString("nomeProjeto"));
                projeto.setLocal(rs.getString("local"));
                projeto.setDataInicio(rs.getDate("dataInicio"));
                projeto.setDataTermino(rs.getDate("dataTermino"));

                projetos.add(projeto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projetos;
    }
}
