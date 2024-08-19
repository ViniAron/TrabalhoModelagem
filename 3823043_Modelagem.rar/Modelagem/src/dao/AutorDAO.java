package dao;

import model.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    public void inserirAutor(Autor autor) {
        String sql = "INSERT INTO Autor(nome, nacionalidade) VALUES(?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, autor.getNome());
            pstmt.setString(2, autor.getNacionalidade());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAutor(Autor autor) {
        String sql = "UPDATE Autor SET nome = ?, nacionalidade = ? WHERE idAutor = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, autor.getNome());
            pstmt.setString(2, autor.getNacionalidade());
            pstmt.setInt(3, autor.getIdAutor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirAutor(int idAutor) {
        String sql = "DELETE FROM Autor WHERE idAutor = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAutor);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Autor> listarAutores() {
        String sql = "SELECT * FROM Autor";
        List<Autor> autores = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor autor = new Autor(
                        rs.getInt("idAutor"),
                        rs.getString("nome"),
                        rs.getString("nacionalidade")
                );
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }
}
