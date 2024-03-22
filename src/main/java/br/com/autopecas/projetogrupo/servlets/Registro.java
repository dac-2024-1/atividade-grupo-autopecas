package br.com.autopecas.projetogrupo.servlets;

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
            getServletContext().getRequestDispatcher("/usuarios/registro.jsp").forward(req, res);
            return;
        }

        if(username.isEmpty() || password.isEmpty()){
            mensagem = "Preencha todos os campos.";
            req.setAttribute("mensagem", mensagem);
            getServletContext().getRequestDispatcher("/usuarios/registro.jsp").forward(req, res);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        UsuarioDao dao;
        try {
            dao = new UsuarioDao();
            dao.registra(usuario);
        } catch (ClassNotFoundException | RuntimeException e) {
            e.printStackTrace();
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/erro.jsp").forward(req, res);
        }
        req.setAttribute("mensagem", mensagem);
        getServletContext().getRequestDispatcher("/usuario/login.jsp").forward(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/usuario/registro.jsp").forward(req, res);
    }
}
