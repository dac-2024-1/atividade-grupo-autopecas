package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.dao.VeiculoDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Veiculo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Veiculo", value = "/veiculo")
public class VeiculoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

        ClienteDao clienteDao = null;
        try {
            clienteDao = new ClienteDao();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Cliente cliente = clienteDao.buscaClientePorId(idCliente);

        veiculo.setCliente(cliente);

        VeiculoDao dao;
        try {
            dao = new VeiculoDao();
            dao.insereVeiculo(veiculo);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("veiculo");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        VeiculoDao dao;
        try {
            dao = new VeiculoDao();
            req.setAttribute("veiculos", dao.buscaTodosVeiculos());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if( req.getParameter("id") != null ) {
            Long id = Long.parseLong(req.getParameter("id"));
            Veiculo veiculo = null;
            List<Veiculo> veiculos = new ArrayList<>();
            req.setAttribute("veiculos", veiculos);
            try {
                dao = new VeiculoDao();
                veiculo = dao.buscaVeiculoPorId(id);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            assert veiculo != null;
            if (veiculo.getId() != null) {
                veiculos.add(veiculo);
                req.setAttribute("veiculos", veiculos);
            }

        }

        req.getRequestDispatcher("/veiculo.jsp").forward(req, res);
    }
}
