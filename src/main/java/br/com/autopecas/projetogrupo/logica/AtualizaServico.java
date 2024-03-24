package br.com.autopecas.projetogrupo.logica;

import br.com.autopecas.projetogrupo.dao.FuncionarioDao;
import br.com.autopecas.projetogrupo.dao.PecaDao;
import br.com.autopecas.projetogrupo.dao.ServicoDao;
import br.com.autopecas.projetogrupo.dao.VeiculoDao;
import br.com.autopecas.projetogrupo.entidades.Funcionario;
import br.com.autopecas.projetogrupo.entidades.Peca;
import br.com.autopecas.projetogrupo.entidades.Servico;
import br.com.autopecas.projetogrupo.entidades.Veiculo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class AtualizaServico implements Logica{
    @Override
    public void executa(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Long id = Long.parseLong(req.getParameter("id"));
        String descricao = req.getParameter("descricao");
        float preco = Float.parseFloat(req.getParameter("preco"));
        Date data = Date.valueOf(req.getParameter("data"));
        Long veiculoId = Long.valueOf(req.getParameter("idVeiculo"));
        Long funcionarioId = Long.valueOf(req.getParameter("idFuncionario"));


        ServicoDao dao;
        VeiculoDao veiculoDao;
        FuncionarioDao funcionarioDao;
        try {
            dao = new ServicoDao();
            veiculoDao = new VeiculoDao();
            funcionarioDao = new FuncionarioDao();
            Servico servico = dao.buscaServicoPorId(id);
            servico.setDescricao(descricao);
            servico.setPreco(preco);
            servico.setData(data.toLocalDate());

            Veiculo veiculo = veiculoDao.buscaVeiculoPorId(veiculoId);
            Funcionario funcionario = funcionarioDao.buscaPorId(funcionarioId);

            servico.setVeiculo(veiculo);
            servico.setFuncionario(funcionario);

            dao.atualizaServico(servico);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        res.sendRedirect("servico");
    }
}
