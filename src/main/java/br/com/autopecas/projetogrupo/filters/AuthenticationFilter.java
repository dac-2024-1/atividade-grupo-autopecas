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
        String requestURI = request.getRequestURI();
        if (request.getSession().getAttribute("username") == null
                // urls que nao precisam de autenticacao
                && !requestURI.endsWith("/usuario/login")
                && !requestURI.endsWith("/usuario/registro")
                && !requestURI.endsWith("/")
                && !requestURI.contains("style")
                ) {
            // pra onde vai redirecionar se o usuario nao estiver logado
            request.getRequestDispatcher("/").forward(request, response);
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