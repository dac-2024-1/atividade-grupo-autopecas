package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Cliente", value = "/cliente")
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);
        System.out.println(cliente);
        ClienteDao dao;
        try {
            dao = new ClienteDao();
            dao.insereCliente(cliente);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("cliente");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ClienteDao dao;
        try {
            dao = new ClienteDao();
            req.setAttribute("clientes", dao.buscaTodosClientes());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("/cliente.jsp").forward(req, res);
    }
}
