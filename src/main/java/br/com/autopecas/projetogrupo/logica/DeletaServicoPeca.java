package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.ServicoPecaDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletaServicoPeca implements Logica{

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));

        ServicoPecaDao dao;
        try {
            dao = new ServicoPecaDao();
            dao.deletaServicoPeca(id);
        } catch (ClassNotFoundException | RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }

        res.sendRedirect("servicopeca");
    }
}
