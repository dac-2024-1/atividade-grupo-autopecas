package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.dao.VeiculoDao;
import br.com.autopecas.projetogrupo.dao.VendaDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Veiculo;
import br.com.autopecas.projetogrupo.entidades.Venda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AtualizaVeiculo implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception{
        Long id = Long.parseLong(req.getParameter("id"));
        Long idCliente = Long.valueOf(req.getParameter("idCliente"));

        VeiculoDao veiculoDao;

        try{
            veiculoDao = new VeiculoDao();
            ClienteDao clienteDao = new ClienteDao();
            Cliente cliente = clienteDao.buscaClientePorId(idCliente);
            Veiculo veiculo = veiculoDao.buscaVeiculoPorId(id);
            veiculo.setCliente(cliente);
            veiculoDao.atualizaVeiculo(veiculo);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        res.sendRedirect("veiculo");
    }
}
