package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Peca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public Peca buscaPecaPorId(Long id) {
        String sql = "select * from peca where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Peca peca = new Peca();
            while (rs.next()) {
                peca.setId(rs.getLong("id"));
                peca.setNome(rs.getString("nome"));
                peca.setDescricao(rs.getString("descricao"));
                peca.setPreco(rs.getFloat("preco"));
                peca.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));
            }
            return peca;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Peca> BuscaTodasPecas(){
        try {
            List<Peca> pecas = new ArrayList<Peca>();
            PreparedStatement stmt = this.connection.prepareStatement("select * " +
                    "from peca");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Peca peca = new Peca();
                peca.setId(rs.getLong("id"));
                peca.setNome(rs.getString("nome"));
                peca.setDescricao(rs.getString("descricao"));
                peca.setPreco(rs.getFloat("preco"));
                peca.setQuantidadeEstoque(rs.getInt("quantidadeEstoque"));

                pecas.add(peca);
            }
            rs.close();
            stmt.close();
            return pecas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void atualizaPeca(Peca peca) {
        String sql = "update peca set preco=?, quantidadeEstoque=? where id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setFloat(1, peca.getPreco());
            stmt.setInt(2, peca.getQuantidadeEstoque());
            stmt.setLong(3, peca.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
