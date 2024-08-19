package dao;

import model.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO Livro(titulo, anoPublicacao, idAutor) VALUES(?, ?, ?)";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setInt(2, livro.getAnoPublicacao());
            pstmt.setInt(3, livro.getIdAutor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE Livro SET titulo = ?, anoPublicacao = ?, idAutor = ? WHERE idLivro = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, livro.getTitulo());
            pstmt.setInt(2, livro.getAnoPublicacao());
            pstmt.setInt(3, livro.getIdAutor());
            pstmt.setInt(4, livro.getIdLivro());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirLivro(int idLivro) {
        String sql = "DELETE FROM Livro WHERE idLivro = ?";

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idLivro);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        String sql = "SELECT * FROM Livro";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("idLivro"),
                        rs.getString("titulo"),
                        rs.getInt("anoPublicacao"),
                        rs.getInt("idAutor")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    public List<Livro> listarLivrosPorAutor(int idAutor) {
        String sql = "SELECT * FROM Livro WHERE idAutor = ?";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConexaoBD.getConexao();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idAutor);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro(
                        rs.getInt("idLivro"),
                        rs.getString("titulo"),
                        rs.getInt("anoPublicacao"),
                        rs.getInt("idAutor")
                );
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
}
