package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Venda;
import br.com.autopecas.projetogrupo.entidades.Peca;
import br.com.autopecas.projetogrupo.entidades.VendaPeca;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaPecaDao {

    private Connection connection;

    public VendaPecaDao() throws  ClassNotFoundException{
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void associaVendaPeca (VendaPeca vendaPeca){
        String sql = "insert into vendaPeca (idVenda, idPeca) values (?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, vendaPeca.getVenda().getId());
            statement.setLong(2, vendaPeca.getPeca().getId());

            statement.execute();
            statement.close();
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public VendaPeca buscaVendaPecaPorId(Long id){
        String sql = "select * from vendaPeca where id=?";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            VendaPeca vendaPeca = new VendaPeca();
            VendaDao vendaDao = new VendaDao();
            PecaDao pecaDao = new PecaDao();

            while (resultSet.next()){
                vendaPeca.setId(resultSet.getLong("id"));
                Venda venda = vendaDao.buscaVendaPorId(resultSet.getLong("idVenda"));
                vendaPeca.setVenda(venda);

                Peca peca = pecaDao.buscaPecaPorId(resultSet.getLong("idPeca"));
                vendaPeca.setPeca(peca);
            }
            return vendaPeca;
        }
        catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public List<VendaPeca> buscaTodasVendaPeca(){
        String sql = "select * from vendaPeca";
        try{
            List<VendaPeca> vendaPecas = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            VendaDao vendaDao = new VendaDao();
            PecaDao pecaDao = new PecaDao();


            while (resultSet.next()){
                VendaPeca vendaPeca = new VendaPeca();
                vendaPeca.setId(resultSet.getLong("id"));

                Venda venda = vendaDao.buscaVendaPorId(resultSet.getLong("idVenda"));
                vendaPeca.setVenda(venda);

                Peca peca = pecaDao.buscaPecaPorId(resultSet.getLong("idPeca"));
                vendaPeca.setPeca(peca);

                vendaPecas.add(vendaPeca);
            }
            resultSet.close();
            statement.close();
            return vendaPecas;
        }
        catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }

    public void deletaVendaPeca(Long id){
        String sql = "delete from vendaPeca where id=?";

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
