package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao {
    private Connection connection;

    public UsuarioDao() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void registra(Usuario usuario){
        String sql = "insert into usuario (username, password) " +
                "values (?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean usuarioExists(Usuario usuario){
        String sql = "select * from usuario where username=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(Usuario usuario) {
        String sql = "select * from usuario where username=? and password=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            return stmt.executeQuery().next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
