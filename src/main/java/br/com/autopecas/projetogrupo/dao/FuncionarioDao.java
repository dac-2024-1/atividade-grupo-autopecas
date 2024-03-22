package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Funcionario;
import br.com.autopecas.projetogrupo.entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {
    /*
    CREATE TABLE funcionario (
    id serial PRIMARY KEY,
    nome varchar(150) NOT NULL,
    cargo varchar(30) NOT NULL,
    endereco varchar(150) NOT NULL,
    telefone varchar(15) NOT NULL,
    salario float NOT NULL,
    dataDeContratacao date NOT NULL,

    idusuario int,
    FOREIGN KEY (idusuario) REFERENCES usuario(id)
);
*/
    private Connection connection;
    UsuarioDao userDao = new UsuarioDao();

    public FuncionarioDao() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void insereFuncionario(Funcionario funcionario) {

        String sql = "insert into funcionario (nome, endereco, telefone, cargo, salario, dataDeContratacao) " +
                "values (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getEndereco());
            stmt.setString(3, funcionario.getTelefone());
            stmt.setString(4, funcionario.getCargo());
            stmt.setFloat(5, funcionario.getSalario());
            stmt.setDate(6, Date.valueOf(funcionario.getDataDeContratacao()));
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void associaUsuario(Long funId, Long userId) {
        String sql = "update funcionario set idusuario = ? where id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, userId);
            stmt.setLong(2, funId);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Funcionario buscaPorId(Long id) {
        String sql = "select * from funcionario where id = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Funcionario funcionario = new Funcionario();
            while (rs.next()) {
                buildFuncionario(rs, funcionario);
            }
            return funcionario;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Funcionario> buscaTodos() {
        List<Funcionario> funs = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement("select * from funcionario");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                buildFuncionario(rs, funcionario);
                funs.add(funcionario);
            }
            rs.close();
            stmt.close();
            return funs;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void deletaFuncionario(Long id){
        try {
            Funcionario funcionario = buscaPorId(id);
            PreparedStatement stmt = connection.prepareStatement("delete from funcionario where id=?");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();

            userDao.deletaUsuario(funcionario.getUsuario().getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildFuncionario(ResultSet rs, Funcionario funcionario) throws SQLException, ClassNotFoundException {
        funcionario.setId(rs.getLong("id"));
        funcionario.setNome(rs.getString("nome"));
        funcionario.setEndereco(rs.getString("endereco"));
        funcionario.setTelefone(rs.getString("telefone"));
        funcionario.setCargo(rs.getString("cargo"));
        funcionario.setSalario(rs.getFloat("salario"));
        funcionario.setDataDeContratacao(rs.getDate("dataDeContratacao").toLocalDate());
        Usuario user = userDao.buscaPorId(rs.getLong("idusuario"));
        funcionario.setUsuario(user);
    }


}