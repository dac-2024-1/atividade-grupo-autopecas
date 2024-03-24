package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public Servico buscaServicoPorId(Long id) {
        String sql = "select * from servico where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            Servico servico = new Servico();
            while (rs.next()) {
                servico.setId(rs.getLong("id"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                servico.setData(rs.getDate("data").toLocalDate());

                VeiculoDao veiculoDao = new VeiculoDao();
                Veiculo veiculo = veiculoDao.buscaVeiculoPorId(rs.getLong("idVeiculo"));
                servico.setVeiculo(veiculo);

                FuncionarioDao funcionarioDao = new FuncionarioDao();
                Funcionario funcionario = funcionarioDao.buscaPorId(rs.getLong("idFuncionario"));
                servico.setFuncionario(funcionario);

            }
            return servico;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Servico> buscaTodosServicos(){
        try {
            List<Servico> servicos = new ArrayList<Servico>();
            PreparedStatement stmt = this.connection.prepareStatement("select * " +
                    "from servico");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Servico servico = new Servico();
                servico.setId(rs.getLong("id"));
                servico.setDescricao(rs.getString("descricao"));
                servico.setPreco(rs.getFloat("preco"));
                servico.setData(rs.getDate("data").toLocalDate());

                VeiculoDao veiculoDao = new VeiculoDao();
                Veiculo veiculo = veiculoDao.buscaVeiculoPorId(rs.getLong("idVeiculo"));
                servico.setVeiculo(veiculo);

                FuncionarioDao funcionarioDao = new FuncionarioDao();
                Funcionario funcionario = funcionarioDao.buscaPorId(rs.getLong("idFuncionario"));
                servico.setFuncionario(funcionario);

                servicos.add(servico);
            }
            rs.close();
            stmt.close();
            return servicos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void atualizaServico(Servico servico) {
        String sql = "update servico set descricao=?, preco=?, data=?, idVeiculo=?, idFuncionario=? where id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, servico.getDescricao());
            stmt.setFloat(2, servico.getPreco());
            stmt.setDate(3, Date.valueOf(servico.getData()));
            stmt.setLong(4, servico.getVeiculo().getId());
            stmt.setLong(5, servico.getFuncionario().getId());
            stmt.setLong(6, servico.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
