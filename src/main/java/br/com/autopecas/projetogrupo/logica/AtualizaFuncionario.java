package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.entidades.Funcionario;
import br.com.autopecas.projetogrupo.dao.FuncionarioDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtualizaFuncionario implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");
        Float salario = Float.parseFloat(req.getParameter("salario"));
        String cargo = req.getParameter("cargo");

        FuncionarioDao dao;
        try {
            dao = new FuncionarioDao();
            Funcionario funcionario = dao.buscaPorId(id);
            funcionario.setEndereco(endereco);
            funcionario.setTelefone(telefone);
            funcionario.setSalario(salario);
            funcionario.setCargo(cargo);
            dao.atualiza(funcionario);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("funcionario");
    }
}
