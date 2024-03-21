package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.VeiculoDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletaVeiculo implements Logica{
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));

        VeiculoDao dao;
        try {
            dao = new VeiculoDao();
            dao.deletaVeiculo(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("veiculo");
    }
}
