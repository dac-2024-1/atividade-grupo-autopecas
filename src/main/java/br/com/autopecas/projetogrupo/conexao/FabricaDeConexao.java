package br.com.autopecas.projetogrupo.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexao {
    public Connection getConnection() throws ClassNotFoundException {
        String user = VarAmbiente.DB_USER;
        String password = VarAmbiente.DB_PASSWORD;
        String dbName = VarAmbiente.DB_NAME;
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, user, password);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
