package travel.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class CharacterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            req.setCharacterEncoding("utf-8");
        }
        String uri = req.getRequestURI();
        if (uri.contains("/bootstrap-5.3.0-dist")) {
            chain.doFilter(request, response);
        } else {
            response.setContentType("text/html;charset=utf-8");
            chain.doFilter(req, resp);
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
