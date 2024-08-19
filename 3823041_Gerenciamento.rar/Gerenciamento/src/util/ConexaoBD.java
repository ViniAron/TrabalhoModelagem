package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static Connection conexao;

    private ConexaoBD() {}

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection("jdbc:sqlite:obras.db");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conexao;
    }
}
