package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.VeiculoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletaVeiculo implements Logica{
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));

        VeiculoDao dao;
        try {
            dao = new VeiculoDao();
            dao.deletaVeiculo(id);
        } catch (ClassNotFoundException | RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }

        res.sendRedirect("veiculo");
    }
}
