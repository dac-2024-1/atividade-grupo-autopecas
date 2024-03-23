package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.dao.PecaDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Peca;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Peca", value = "/peca")
public class PecaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String descricao = req.getParameter("descricao");
        String preco = req.getParameter("preco");
        String quantidadeEstoque = req.getParameter("quantidadeEstoque");

        Peca peca = new Peca();
        peca.setNome(nome);
        peca.setDescricao(descricao);
        peca.setPreco(Float.valueOf(preco));
        peca.setQuantidadeEstoque(Integer.parseInt(quantidadeEstoque));

        PecaDao dao;
        try {
            dao = new PecaDao();
            dao.inserePeca(peca);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("peca");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/peca.jsp").forward(req, res);
    }

}
