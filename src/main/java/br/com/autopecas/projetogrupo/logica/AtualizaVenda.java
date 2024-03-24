package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.VendaDao;
import br.com.autopecas.projetogrupo.entidades.Venda;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class AtualizaVenda implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception{
        Long id = Long.parseLong(req.getParameter("id"));
        LocalDate data = LocalDate.parse(req.getParameter("data"));
        Float totalVenda = Float.parseFloat(req.getParameter("totalVenda"));

        VendaDao vendaDao;

        try{
            vendaDao = new VendaDao();
            Venda venda = vendaDao.buscaVendaPorId(id);
            venda.setData(data);
            venda.setTotalVenda(totalVenda);
            vendaDao.atualizaVenda(venda);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        res.sendRedirect("venda");
    }
}
