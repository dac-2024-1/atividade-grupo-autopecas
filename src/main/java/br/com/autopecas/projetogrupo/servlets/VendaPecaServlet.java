package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.PecaDao;
import br.com.autopecas.projetogrupo.dao.VendaPecaDao;

import br.com.autopecas.projetogrupo.entidades.Peca;
import br.com.autopecas.projetogrupo.entidades.VendaPeca;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "VendaPeca", value = "/vendaPeca")
public class VendaPecaServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Long idVenda = Long.parseLong(req.getParameter("idVenda"));
        Long idPeca = Long.parseLong(req.getParameter("idPeca"));

        try{
        VendaPeca vendaPeca = new VendaPeca();
        VendaDao vendaDao = new VendaDao();
        PecaDao pecaDao = new PecaDao();

        vendaPeca.setId(id);
        Venda venda = vendaDao.buscaVendaPorId(idVenda);
        vendaPeca.setVenda(venda);

        Peca peca = pecaDao.buscaPecaPorId(idPeca);
        vendaPeca.setPeca(peca);

        VendaPecaDao vendaPecaDao = new VendaPecaDao();
        vendaPecaDao.associaVendaPeca(vendaPeca);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        res.sendRedirect("vendaPeca");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        VendaPecaDao vendaPecaDao;

        try{
            vendaPecaDao = new VendaPecaDao();
            req.setAttribute("vendaPecas", vendaPecaDao.buscaTodasVendaPeca());
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String idString = req.getParameter("id");
            if(idString != null && !idString.trim().isEmpty()){
                Long id = Long.parseLong(idString);
                VendaPeca vendaPeca = null;
                List<VendaPeca> vendaPecas = new ArrayList<>();
                req.setAttribute("vendaPecas", vendaPecas);

                try{
                    vendaPecaDao = new VendaPecaDao();
                    vendaPeca = vendaPecaDao.buscaVendaPecaPorId(id);
                }
                catch (ClassNotFoundException | RuntimeException e){{
                    e.printStackTrace();
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/erro.jsp").forward(req, res);
                }
                }
                assert vendaPeca != null;
                if(vendaPeca.getId() != null){
                    vendaPecas.add(vendaPeca);
                }
            }
            req.getRequestDispatcher("/vendaPeca.jsp").forward(req, res);
        }
    }
}
