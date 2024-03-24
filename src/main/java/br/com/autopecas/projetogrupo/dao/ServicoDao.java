package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Peca;
import br.com.autopecas.projetogrupo.entidades.Servico;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicoDao {
    private Connection connection;

    public ServicoDao() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void insereServico(Servico servico){
        String sql = "insert into servico (descricao, preco, data, idVeiculo, idFuncionario)" +
                "values (?,?,?,?,?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getDescricao());
            stmt.setFloat(2, servico.getPreco());
            stmt.setDate(3, Date.valueOf(servico.getData()));
            stmt.setLong(4, servico.getVeiculo().getId());
            stmt.setLong(5, servico.getFuncionario().getId());


            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
