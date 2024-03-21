package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtualizaCliente implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));
        String endereco = req.getParameter("endereco");
        String telefone = req.getParameter("telefone");

        ClienteDao dao;
        try {
            dao = new ClienteDao();
            Cliente cliente = dao.buscaClientePorId(id);
            cliente.setEndereco(endereco);
            cliente.setTelefone(telefone);
            dao.atualizaCliente(cliente);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("cliente");
    }
}
