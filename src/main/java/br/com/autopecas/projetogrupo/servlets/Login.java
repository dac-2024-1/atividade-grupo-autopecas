package br.com.autopecas.projetogrupo.servlets;

import br.com.autopecas.projetogrupo.dao.UsuarioDao;
import br.com.autopecas.projetogrupo.entidades.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Login", value = "/usuario/login")
public class Login extends HttpServlet {
    String loginPath = "/usuario/login.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Usuario user = new Usuario();
        user.setUsername(username);
        user.setPassword(password);

        try {
            UsuarioDao userDao = new UsuarioDao();
            if (userDao.checkPassword(user)) {
                req.getSession().setAttribute("username", username);
                res.sendRedirect("/paginaInicial.html");
                return;
            }

        } catch (RuntimeException | ClassNotFoundException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }
        req.setAttribute("mensagem", "Usuário ou senha inválidos. Preencha os campos corretamente.");
        getServletContext().getRequestDispatcher(loginPath).forward(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getSession().getAttribute("username") != null) {
            res.sendRedirect(req.getContextPath() + "/paginaInicial.html");
        } else {
            req.getRequestDispatcher(loginPath).forward(req, res);
        }
    }
}
