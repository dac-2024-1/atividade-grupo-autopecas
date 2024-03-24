package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Servico;
import br.com.autopecas.projetogrupo.entidades.ServicoPeca;
import br.com.autopecas.projetogrupo.entidades.Peca;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicoPecaDao {

    private Connection connection;

    public ServicoPecaDao() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void registraServicoPeca(ServicoPeca servicoPeca) {

        String sql = "insert into servicopeca (idServico,idPeca) values (?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, servicoPeca.getServico().getId());
            stmt.setLong(2, servicoPeca.getPeca().getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ServicoPeca buscaServicoPecaPorId(Long id) {
        String sql = "select * from servicopeca where id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            ServicoPeca servicoPeca = new ServicoPeca();
            while (rs.next()){
                servicoPeca.setId(rs.getLong("id"));
                ServicoDao servicoDao = new ServicoDao();
                Servico servico = servicoDao.buscaServicoPorId(rs.getLong("idServico"));
                servicoPeca.setServico(servico);
                PecaDao pecaDao = new PecaDao();
                Peca peca = pecaDao.buscaPecaPorId(rs.getLong("idPeca"));
                servicoPeca.setPeca(peca);
            }
            rs.close();
            stmt.close();
            return servicoPeca;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ServicoPeca> buscaTodosServicoPeca(){
        String sql = "select * from servicopeca";
        List<ServicoPeca> servicosPecas = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ServicoPeca servicoPeca = new ServicoPeca();
                servicoPeca.setId(rs.getLong("id"));
                ServicoDao servicoDao = new ServicoDao();
                Servico servico = servicoDao.buscaServicoPorId(rs.getLong("idServico"));
                servicoPeca.setServico(servico);
                PecaDao pecaDao = new PecaDao();
                Peca peca = pecaDao.buscaPecaPorId(rs.getLong("idPeca"));
                servicoPeca.setPeca(peca);
                servicosPecas.add(servicoPeca);
            }
            rs.close();
            stmt.close();
            return servicosPecas;
        }catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletaServicoPeca(Long id){
        String sql = "delete * from servicopeca where id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1,id);
            stmt.execute();
            stmt.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
