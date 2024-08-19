package dao;

import model.Material;
import util.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    public void inserirMaterial(Material material) {
        String sql = "INSERT INTO Material (nomeMaterial, quantidade) VALUES (?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarMaterial(Material material) {
        String sql = "UPDATE Material SET nomeMaterial = ?, quantidade = ? WHERE idMaterial = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.setInt(3, material.getIdMaterial());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirMaterial(int idMaterial) {
        String sql = "DELETE FROM Material WHERE idMaterial = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Material> listarMateriais() {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM Material";

        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("idMaterial"));
                material.setNomeMaterial(rs.getString("nomeMaterial"));
                material.setQuantidade(rs.getInt("quantidade"));

                materiais.add(material);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiais;
    }

    public List<Material> listarMateriaisPorProjeto(int idProjeto) {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT m.* FROM Material m INNER JOIN Consumo_Material cm ON m.idMaterial = cm.idMaterial WHERE cm.idProjeto = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Material material = new Material();
                material.setIdMaterial(rs.getInt("idMaterial"));
                material.setNomeMaterial(rs.getString("nomeMaterial"));
                material.setQuantidade(rs.getInt("quantidade"));

                materiais.add(material);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiais;
    }
}
