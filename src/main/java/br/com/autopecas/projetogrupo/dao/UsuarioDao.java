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

    public void updatePassword(Usuario usuario) {
        String sql = "update usuario set password=? where username=?";
        try {
            String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, hashedPassword);
            stmt.setString(2, usuario.getUsername());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario buscaPorId(Long id) {
        String sql = "select * from usuario where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Usuario usuario = new Usuario();
            while (rs.next()) {
                usuario.setId(rs.getLong("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
            }
            return usuario;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletaUsuario(Long id){
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from usuario where id=?");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario buscaPorUsername(String username){
        String sql = "select * from usuario where username=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            Usuario user = null;
            while (rs.next()) {
                user = new Usuario();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Usuario usuario) {
        String sql = "delete from usuario where id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, usuario.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
