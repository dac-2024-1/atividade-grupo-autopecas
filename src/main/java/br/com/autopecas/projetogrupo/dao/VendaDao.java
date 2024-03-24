package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;

import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Venda;
import br.com.autopecas.projetogrupo.entidades.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDao {

    private Connection connection;

    public VendaDao() throws ClassNotFoundException{
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void efetuaVenda (Venda venda){
        String sql = "insert into venda (data, totalVenda, idCliente, idFuncionario) values (?,?,?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(venda.getData()));
            statement.setFloat(2, venda.getTotalVenda());
            statement.setLong(3, venda.getCliente().getId());
            statement.setLong(4, venda.getFuncionario().getId());

            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Venda buscaVendaPorId(Long id){
        String sql = "select * from venda where id=?";

        try{
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Venda venda = new Venda();
            ClienteDao clienteDao = new ClienteDao();
            FuncionarioDao funcionarioDao = new FuncionarioDao();

            while (resultSet.next()){
                venda.setId(resultSet.getLong("id"));
                venda.setData(resultSet.getDate("data").toLocalDate());
                venda.setTotalVenda(resultSet.getFloat("totalVenda"));
                Cliente cliente = clienteDao.buscaClientePorId(resultSet.getLong("id"));
                venda.setCliente(cliente);
                Funcionario funcionario = funcionarioDao.buscaPorId(resultSet.getLong("id"));
                venda.setFuncionario(funcionario);
            }
            return  venda;
        }
        catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public List<Venda> buscaTodasVendas() {
        String sql = "Select * from venda";

        try{
            List<Venda> vendas = new ArrayList<>();
            PreparedStatement statement = this.connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Venda venda = new Venda();
                venda.setId(resultSet.getLong("id"));
                venda.setData(resultSet.getDate("data").toLocalDate());
                venda.setTotalVenda(resultSet.getFloat("totalVenda"));

                ClienteDao clienteDao = new ClienteDao();
                Cliente cliente = clienteDao.buscaClientePorId(resultSet.getLong("id"));
                venda.setCliente(cliente);

                FuncionarioDao funcionarioDao = new FuncionarioDao();
                Funcionario funcionario = funcionarioDao.buscaPorId(resultSet.getLong("id"));
                venda.setFuncionario(funcionario);

                vendas.add(venda);
            }
            resultSet.close();
            statement.close();
            return vendas;
        }
        catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void atualizaVenda (Venda venda){
        String sql = "update venda set data=?, totalVenda=? where id=?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(venda.getData()));
            statement.setFloat(2, venda.getTotalVenda());
            statement.setLong(3,venda.getId());
            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void deletaVenda (Long id){
        String sql = "delete from venda where id=?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}

