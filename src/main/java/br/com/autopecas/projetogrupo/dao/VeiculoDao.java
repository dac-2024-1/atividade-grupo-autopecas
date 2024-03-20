package br.com.autopecas.projetogrupo.dao;

import br.com.autopecas.projetogrupo.conexao.FabricaDeConexao;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDao {
    private Connection connection;

    public VeiculoDao() throws ClassNotFoundException {
        this.connection = new FabricaDeConexao().getConnection();
    }

    public void insereVeiculo(Veiculo veiculo){
        String sql = "insert into veiculo (marca, modelo, ano, placa, idcliente) " +
                "values (?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getMarca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getAno());
            stmt.setString(4, veiculo.getPlaca());
            stmt.setLong(3, veiculo.getCliente().getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Veiculo buscaVeiculoPorId(Long id) {
        String sql = "select * from veiculo where id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            ClienteDao clienteDao = new ClienteDao();
            Veiculo veiculo = new Veiculo();
            while (rs.next()) {
                veiculo.setId(rs.getLong("id"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getString("ano"));
                veiculo.setPlaca(rs.getString("placa"));
                Cliente cliente = clienteDao.buscaClientePorId(rs.getLong("idcliente"));
                veiculo.setCliente(cliente);
            }
            return veiculo;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Veiculo> buscaTodosVeiculos(){
        try {
            List<Veiculo> veiculos = new ArrayList<Veiculo>();
            PreparedStatement stmt = this.connection.prepareStatement("select * " +
                    "from veiculo");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getLong("id"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getString("ano"));
                veiculo.setPlaca(rs.getString("placa"));
                ClienteDao clienteDao = new ClienteDao();
                Cliente cliente = clienteDao.buscaClientePorId(rs.getLong("idcliente"));
                veiculo.setCliente(cliente);

                veiculos.add(veiculo);
            }
            rs.close();
            stmt.close();
            return veiculos;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void atualizaVeiculo(Veiculo veiculo) {
        String sql = "update veiculo set idcliente=? where id=?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, veiculo.getCliente().getId());
            stmt.setLong(1, veiculo.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletaVeiculo(Long id){
        try {
            PreparedStatement stmt = connection.prepareStatement("delete from veiculo" +
                    " where id=?");
            stmt.setLong(1, id);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
