package no.hib.mod250.anthrax.filter;

import no.hib.mod250.anthrax.presentation.AccountBean;

import javax.faces.application.ResourceHandler;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter that saves the referer when logging in or registering, in able to return to the previous page
 */
@WebFilter("/account/*")
public class ReturnURLFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String loginURL = request.getContextPath() + "/account/login.xhtml";
        String registerURL = request.getContextPath() + "/account/signup.xhtml";
        String referer = request.getHeader("Referer");
        boolean returnAfterLogin = request.getParameter("return-after-login") != null;
        boolean doReferal = referer != null && !referer.contains(loginURL) && !referer.contains(registerURL);


        if (returnAfterLogin && doReferal) {
            HttpSession session = request.getSession(true);
            session.setAttribute("requestedURL", referer); //Puts the requested URL in session-storage
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
