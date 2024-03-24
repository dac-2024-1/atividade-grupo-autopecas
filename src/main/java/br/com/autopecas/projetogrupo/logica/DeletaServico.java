package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.PecaDao;
import br.com.autopecas.projetogrupo.dao.ServicoDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletaServico implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));

        ServicoDao dao;
        try {
            dao = new ServicoDao();
            dao.deletaServico(id);
        } catch (ClassNotFoundException | RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }

        res.sendRedirect("servico");
    }
}
