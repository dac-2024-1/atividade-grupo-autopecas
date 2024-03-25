package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.FuncionarioDao;
import br.com.autopecas.projetogrupo.dao.UsuarioDao;
import br.com.autopecas.projetogrupo.entidades.Usuario;
import br.com.autopecas.projetogrupo.entidades.Funcionario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Usuario", value = {"/usuario"})
public class UsuarioServlet extends HttpServlet {
    String usuarioPath = "/usuario/index.jsp";
    String loginPath = "/usuario/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Object sessionUsername = req.getSession().getAttribute("username");
        if (sessionUsername == null) {
            res.sendRedirect(req.getContextPath() + "/usuario/login");
            return;
        }
        UsuarioDao userDao = null;
        FuncionarioDao funcionarioDao = null;
        try {
            userDao = new UsuarioDao();
            funcionarioDao = new FuncionarioDao();
            Usuario usuario = userDao.buscaPorUsername((String) sessionUsername);
            req.setAttribute("usuario", usuario);
            Funcionario funcionarioAssociado = funcionarioDao.buscaPorUserId(usuario.getId());
            req.setAttribute("funcionario", funcionarioAssociado);
        } catch (ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
        }
        getServletContext().getRequestDispatcher(usuarioPath).forward(req, res);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        String senhaAtual = req.getParameter("senhaAtual");
        String newPassword = req.getParameter("newPassword");
        String confirmaNewPassword = req.getParameter("confirmaNewPassword");

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(senhaAtual);
        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            String mensagem = "Erro: Senha incorreta ou nao confirmada.";
            if (!newPassword.equals(confirmaNewPassword)){
                req.setAttribute("mensagem", mensagem);
                req.getRequestDispatcher(usuarioPath).forward(req, resp);
                return;
            }

            if (usuarioDao.checkPassword(usuario)) {
                usuario.setPassword(newPassword);
                usuarioDao.updatePassword(usuario);
                resp.sendRedirect(req.getContextPath() + "/usuario/logout");

            } else {
                req.setAttribute("mensagem", mensagem);
                req.getRequestDispatcher(usuarioPath).forward(req, resp);
            }

        } catch (ClassNotFoundException e) {
            req.setAttribute("error", e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");

        try {
            UsuarioDao usuarioDao = new UsuarioDao();
            Usuario usuario = usuarioDao.buscaPorUsername(username);
            usuarioDao.delete(usuario);
            req.getSession().invalidate();
            res.setStatus(HttpServletResponse.SC_OK);
            res.getWriter().write("Conta deletada com sucesso");
        } catch (ClassNotFoundException e) {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res.getWriter().write("Error deleting account: " + e.getMessage());
        }
    }
}
