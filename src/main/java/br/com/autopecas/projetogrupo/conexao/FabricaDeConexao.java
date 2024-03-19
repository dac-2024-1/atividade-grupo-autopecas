package br.com.autopecas.projetogrupo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {
    public Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/dac-autopecas",
                    "postgres", "dandara");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
