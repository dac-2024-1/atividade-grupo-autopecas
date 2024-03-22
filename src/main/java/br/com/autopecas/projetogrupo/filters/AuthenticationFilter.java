package br.com.autopecas.projetogrupo.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Comentar esse if pra desativar autenticaçao enquanto desenvolve
        if (request.getSession().getAttribute("username") == null
                && !request.getRequestURI().endsWith("/usuario/login")
                && !request.getRequestURI().endsWith("/usuario/registro")) {
            request.setAttribute("mensagem", "Você precisa estar logado para acessar essa página.");
            request.getRequestDispatcher("/usuario/login.jsp").forward(request, response);
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}