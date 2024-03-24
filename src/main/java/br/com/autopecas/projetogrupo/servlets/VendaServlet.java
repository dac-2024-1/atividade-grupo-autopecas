package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.ClienteDao;
import br.com.autopecas.projetogrupo.dao.FuncionarioDao;
import br.com.autopecas.projetogrupo.dao.VendaDao;
import br.com.autopecas.projetogrupo.entidades.Cliente;
import br.com.autopecas.projetogrupo.entidades.Funcionario;
import br.com.autopecas.projetogrupo.entidades.Venda;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "Venda", value = "/venda")
public class VendaServlet extends  HttpServlet{

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        LocalDate data = LocalDate.parse(req.getParameter("data"));
        Float totalVenda = Float.valueOf(req.getParameter("totalVenda"));
        Long idCliente = Long.parseLong(req.getParameter("idCliente"));
        Long idFuncionario = Long.parseLong(req.getParameter("idFuncionario"));

        try {
            VendaDao vendaDao = new VendaDao();
            Venda venda = new Venda();
            venda.setData(data);
            venda.setTotalVenda(totalVenda);

            ClienteDao clienteDao = new ClienteDao();
            Cliente cliente = clienteDao.buscaClientePorId(idCliente);
            venda.setCliente(cliente);

            FuncionarioDao funcionarioDao = new FuncionarioDao();
            Funcionario funcionario = funcionarioDao.buscaPorId(idFuncionario);
            venda.setFuncionario(funcionario);

            vendaDao.efetuaVenda(venda);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        res.sendRedirect("venda");
    }

    @Override
    protected  void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            VendaDao vendaDao;
            try {
                vendaDao = new VendaDao();
                req.setAttribute("vendas", vendaDao.buscaTodasVendas());
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String idString = req.getParameter("id");
            if(idString != null && idString.trim().isEmpty()){
                Long id = Long.parseLong(idString);
                Venda venda =  null;
                List<Venda> vendas = new ArrayList<>();
                req.setAttribute("vendas", vendas);

                try{
                    vendaDao = new VendaDao();
                    venda = vendaDao.buscaVendaPorId(id);
                }
                catch (ClassNotFoundException | RuntimeException e){
                    e.printStackTrace();
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/erro.jsp").forward(req, res);
                }
                assert venda != null;
                if (venda.getId() != null){
                    vendas.add(venda);
                }
            }
        req.getRequestDispatcher("/venda.jsp").forward(req, res);
    }
}
