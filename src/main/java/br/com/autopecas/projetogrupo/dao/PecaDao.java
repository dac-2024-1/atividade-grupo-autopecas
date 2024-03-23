package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Peca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PecaDao {

    private Connection connection;

    public PecaDao() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void inserePeca(Peca peca){
        String sql = "insert into peca (nome, descricao, preco, quantidadeEstoque)" +
                "values (?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, peca.getNome());
            stmt.setString(2, peca.getDescricao());
            stmt.setFloat(3, peca.getPreco());
            stmt.setInt(4, peca.getQuantidadeEstoque());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
