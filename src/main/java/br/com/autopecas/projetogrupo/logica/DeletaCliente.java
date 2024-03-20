package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.ClienteDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletaCliente implements Logica {
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));

        ClienteDao dao;
        try {
            dao = new ClienteDao();
            dao.deletaCliente(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher rd = req.getRequestDispatcher("/cliente.jsp");
        rd.forward(req, res);
    }
}
