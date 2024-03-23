package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.FuncionarioDao;
import br.com.autopecas.projetogrupo.dao.UsuarioDao;
import br.com.autopecas.projetogrupo.entidades.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistraUsuario", value = "/usuario/registro")
public class Registro extends HttpServlet {
    String registroPath = "/usuario/registro.jsp";
    String loginPath = "/usuario/login.jsp";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmaSenha = req.getParameter("confirmaSenha");
        String idFuncionario = req.getParameter("idFuncionario");

        String mensagem = "Registro feito com sucesso! Faça login para continuar.";

        if(!password.equals(confirmaSenha)){
            mensagem = "Senhas não conferem.";
            req.setAttribute("mensagem", mensagem);
            getServletContext().getRequestDispatcher(registroPath).forward(req, res);
            return;
        }

        if(username.isEmpty() || password.isEmpty()){
            mensagem = "Preencha todos os campos.";
            req.setAttribute("mensagem", mensagem);
            getServletContext().getRequestDispatcher(registroPath).forward(req, res);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        UsuarioDao userDao;

        try {
            userDao = new UsuarioDao();
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            userDao.registra(usuario);

            if(idFuncionario != null && !idFuncionario.isEmpty()){
                long longFunId = Long.parseLong(idFuncionario);
                funcionarioDao.associaUsuario(longFunId, userDao.buscaIdPorUsername(username));
            }

        } catch (ClassNotFoundException | RuntimeException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }

        req.setAttribute("mensagem", mensagem);
        getServletContext().getRequestDispatcher(loginPath).forward(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            req.setAttribute("funcionarios", new FuncionarioDao().buscaTodosSemUsuario());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }
        if (req.getSession().getAttribute("username") != null) {
            res.sendRedirect(req.getContextPath() + "/");
        } else {
            getServletContext().getRequestDispatcher(registroPath).forward(req, res);
        }
    }
}
