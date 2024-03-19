package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdicionaCliente implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String nome = req.getParameter("nome");
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);

        ClienteDao dao;
        try {
            dao = new ClienteDao();
            dao.insereCliente(cliente);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = req.getRequestDispatcher("/cliente-adicionado.jsp");
        rd.forward(req, res);
    }

}
