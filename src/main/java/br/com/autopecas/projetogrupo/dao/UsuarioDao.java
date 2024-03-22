package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Usuario;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, hashedPassword);
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
        String sql = "select password from usuario where username=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String hashedPassword = rs.getString("password");
                return BCrypt.checkpw(usuario.getPassword(), hashedPassword);
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
