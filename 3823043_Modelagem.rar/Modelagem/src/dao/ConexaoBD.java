package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static Connection conexao;

    private ConexaoBD() {
        // Construtor privado para evitar instanciamento externo
    }

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection("jdbc:sqlite:livraria.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexao;
    }
}
