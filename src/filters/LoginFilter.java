package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Trainee;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter implements Filter {

    /**
     * Default constructor.
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String context_path = ((HttpServletRequest)request).getContextPath();
        String servlet_path = ((HttpServletRequest)request).getServletPath();

        if(!(servlet_path.matches("/css.*") || servlet_path.matches("/trainees/new") || servlet_path.matches("/trainees/create"))) {
            HttpSession session = ((HttpServletRequest)request).getSession();
            Trainee t = (Trainee)session.getAttribute("login_trainee");

            if(!servlet_path.equals("/login")) {
                if(t == null) {
                    ((HttpServletResponse)response).sendRedirect(context_path + "/login");
                    return;
                }
            } else {
                if(t != null) {
                    ((HttpServletResponse)response).sendRedirect(context_path + "/trainees/index");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
