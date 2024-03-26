package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.PecaDao;
import br.com.autopecas.projetogrupo.dao.ServicoDao;
import br.com.autopecas.projetogrupo.dao.ServicoPecaDao;
import br.com.autopecas.projetogrupo.entidades.Peca;
import br.com.autopecas.projetogrupo.entidades.Servico;
import br.com.autopecas.projetogrupo.entidades.ServicoPeca;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "servicoPeca", urlPatterns = "/servicoPeca")
public class ServicoPecaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String idServico = req.getParameter("idServico");
        String idPeca = req.getParameter("idPeca");

        ServicoPeca servicoPeca = new ServicoPeca();
        try {
            ServicoDao servicoDao = new ServicoDao();
            Servico servico = servicoDao.buscaServicoPorId(Long.parseLong(idServico));
            servicoPeca.setServico(servico);
            PecaDao pecaDao = new PecaDao();
            Peca peca = pecaDao.buscaPecaPorId(Long.parseLong(idPeca));
            servicoPeca.setPeca(peca);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ServicoPecaDao dao;
        try {
            dao = new ServicoPecaDao();
            System.out.printf(servicoPeca.getServico().getId().toString());
            dao.registraServicoPeca(servicoPeca);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("servicoPeca");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServicoPecaDao dao;
        try {
            dao = new ServicoPecaDao();
            PecaDao pecaDao = new PecaDao();
            ServicoDao servicoDao = new ServicoDao();
            req.setAttribute("servicospecas", dao.buscaTodosServicoPeca());
            req.setAttribute("servicos", servicoDao.buscaTodosServicos());
            req.setAttribute("pecas", pecaDao.BuscaTodasPecas());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String idStr = req.getParameter("id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            Long id = Long.parseLong(idStr);
            ServicoPeca servicoPeca = null;
            List<ServicoPeca> servicosPecas = new ArrayList<>();
            req.setAttribute("servicosPecas", servicosPecas);
            try {
                dao = new ServicoPecaDao();
                servicoPeca = dao.buscaServicoPecaPorId(id);
            } catch (ClassNotFoundException | RuntimeException e) {
                e.printStackTrace();
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/erro.jsp").forward(req, res);
            }

            assert servicoPeca != null;
            if (servicoPeca.getId() != null){
                servicosPecas.add(servicoPeca);
            }
        }

        req.getRequestDispatcher("/servicoPeca.jsp").forward(req, res);
    }

}