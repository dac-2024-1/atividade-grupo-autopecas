package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.VendaDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class DeletaVenda implements Logica{

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));

        VendaDao vendaDao;

        try{
            vendaDao = new VendaDao();
            vendaDao.deletaVenda(id);
        }
        catch (ClassNotFoundException | RuntimeException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }
        res.sendRedirect("venda");
    }
}
