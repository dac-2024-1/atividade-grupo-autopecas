package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.FuncionarioDao;
import br.com.autopecas.projetogrupo.entidades.Funcionario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Funcionario", value = {"/funcionario", "/usuario/registro/funcionario"})
public class FuncionarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String cargo = req.getParameter("cargo");
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");
        String salario = req.getParameter("salario");
        String dataContratacao = req.getParameter("dataContratacao");

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setEndereco(endereco);
        funcionario.setTelefone(telefone);
        funcionario.setSalario(Float.parseFloat(salario));
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao));

        System.out.println(LocalDate.parse(dataContratacao));

        FuncionarioDao dao;
        try {
            dao = new FuncionarioDao();
            dao.insereFuncionario(funcionario);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("funcionario");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Funcionario> funSemUsuario = new ArrayList<>();
        try {
            FuncionarioDao dao = new FuncionarioDao();
            funSemUsuario = dao.buscaTodosSemUsuario();
            req.setAttribute("funcionarios", dao.buscaTodos());
            Funcionario funFiltrado = null;

            String idStr = req.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                Long id = Long.parseLong(idStr);
                List<Funcionario> funcionarios = new ArrayList<>();
                funFiltrado = dao.buscaPorId(id);

                assert funFiltrado != null;
                if (funFiltrado.getId() != null) {
                    funcionarios.add(funFiltrado);
                }
                if (funFiltrado.getUsuario() != null) {
                    funSemUsuario = new ArrayList<>();
                    funSemUsuario.add(funFiltrado);
                }
                req.setAttribute("funcionarios", funcionarios);

            }
        } catch (ClassNotFoundException | RuntimeException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }

        if (req.getRequestURI().endsWith("/usuario/registro/funcionario")) {
            req.setAttribute("funcionarios", funSemUsuario);
            req.getRequestDispatcher("/usuario/registro.jsp").forward(req, res);
        } else if (req.getRequestURI().endsWith("/funcionario")) {
            req.getRequestDispatcher("/funcionario.jsp").forward(req, res);
        }
    }
}
