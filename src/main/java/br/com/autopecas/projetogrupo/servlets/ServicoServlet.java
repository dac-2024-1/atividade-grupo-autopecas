package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.*;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Peca;
import br.com.autopecas.projetogrupo.entidades.Servico;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "Servico", value = "/servico")
public class ServicoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String descricao = req.getParameter("descricao");
        String preco = req.getParameter("preco");
        String data = req.getParameter("data");
        Long idVeiculo = Long.valueOf(req.getParameter("idVeiculo"));
        Long idFuncionario = Long.valueOf(req.getParameter("idFuncionario"));


        ServicoDao dao;
        VeiculoDao veiculoDao;
        FuncionarioDao funcionarioDao;

        try {
            dao = new ServicoDao();
            veiculoDao = new VeiculoDao();
            funcionarioDao = new FuncionarioDao();

            Servico servico = new Servico();
            servico.setDescricao(descricao);
            servico.setPreco(Float.valueOf(preco));
            servico.setData(LocalDate.parse(data));
            servico.setVeiculo(veiculoDao.buscaVeiculoPorId(idVeiculo));
            servico.setFuncionario(funcionarioDao.buscaPorId(idFuncionario));


            dao.insereServico(servico);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        res.sendRedirect("servico");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServicoDao dao;
        VeiculoDao veiculoDao;
        FuncionarioDao funcionarioDao;
        try {
            dao = new ServicoDao();
            veiculoDao = new VeiculoDao();
            funcionarioDao = new FuncionarioDao();
            req.setAttribute("servicos", dao.buscaTodosServicos());
            req.setAttribute("veiculos", veiculoDao.buscaTodosVeiculos());
            req.setAttribute("funcionarios", funcionarioDao.buscaTodosSemUsuario());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String idStr = req.getParameter("id");
        if( idStr != null && !idStr.trim().isEmpty()) {
            Long id = Long.parseLong(idStr);
            Servico servico = null;
            List<Servico> servicos = new ArrayList<>();
            req.setAttribute("servicos", servicos);
            try {
                dao = new ServicoDao();
                servico = dao.buscaServicoPorId(id);
            } catch (ClassNotFoundException | RuntimeException e) {
                e.printStackTrace();
                req.setAttribute("error", e.getMessage());
                req.getRequestDispatcher("/erro.jsp").forward(req, res);
            }
            assert servico != null;
            if (servico.getId() != null) {
                servicos.add(servico);
            }
        }
        req.getRequestDispatcher("/servico.jsp").forward(req, res);
    }
}



