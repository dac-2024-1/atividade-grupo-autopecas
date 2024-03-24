package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.VendaPecaDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletaVendaPeca implements Logica{

    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception{
        Long id = Long.parseLong(req.getParameter("id"));

        VendaPecaDao vendaPecaDao;
        try{
            vendaPecaDao = new VendaPecaDao();
            vendaPecaDao.deletaVendaPeca(id);
        }
        catch (ClassNotFoundException | RuntimeException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }
        res.sendRedirect("vendaPeca");
    }
}
