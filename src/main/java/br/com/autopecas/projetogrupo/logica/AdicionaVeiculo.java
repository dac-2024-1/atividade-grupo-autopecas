package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.dao.VeiculoDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Veiculo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdicionaVeiculo implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String marca = req.getParameter("marca");
        String modelo = req.getParameter("modelo");
        String ano = req.getParameter("ano");
        String placa = req.getParameter("placa");
        Long idCliente = Long.parseLong(req.getParameter("idcliente"));

        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setAno(ano);
        veiculo.setPlaca(placa);

        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.buscaClientePorId(idCliente);

        veiculo.setCliente(cliente);

        VeiculoDao dao;
        try {
            dao = new VeiculoDao();
            dao.insereVeiculo(veiculo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("veiculo.jsp");
    }
}
