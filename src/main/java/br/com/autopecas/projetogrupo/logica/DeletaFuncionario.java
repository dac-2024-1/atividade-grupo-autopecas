package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.FuncionarioDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletaFuncionario implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));

        FuncionarioDao dao;
        try {
            dao = new FuncionarioDao();
            dao.deletaFuncionario(id);
        } catch (ClassNotFoundException | RuntimeException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }

        res.sendRedirect("funcionario");
    }

}
