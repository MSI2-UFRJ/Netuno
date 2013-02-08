package br.com.ufrj.msi2.netuno.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * Invalida a sess�o do usu�rio. Deve ser usado para deslogar um usu�rio
 * @author Wakim Jraige
 *
 */
public class LogoutFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		servletRequest.getSession(false).invalidate();
		servletResponse.sendRedirect("/");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}

}
