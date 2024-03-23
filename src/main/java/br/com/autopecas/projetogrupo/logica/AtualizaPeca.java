package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.dao.PecaDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Peca;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AtualizaPeca  implements  Logica{
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));
        float preco = Float.parseFloat(req.getParameter("preco"));
        int quantidadeEstoque = Integer.parseInt(req.getParameter("quantidadeEstoque"));

        PecaDao dao;
        try {
            dao = new PecaDao();
            Peca peca = dao.buscaPecaPorId(id);
            peca.setPreco(preco);
            peca.setQuantidadeEstoque(quantidadeEstoque);
            dao.atualizaPeca(peca);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("peca");
    }

}
