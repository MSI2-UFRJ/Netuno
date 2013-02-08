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
import javax.servlet.http.HttpSession;

import br.com.ufrj.msi2.netuno.attributes.Attributes;

/***
 * Filtro resposável por redirecionar o usuário logado do index para a tela de dashboard
 * @author Wakim Jraige
 *
 */
public class LoginFilter implements Filter {

	String urlDestino;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession(false);
		
		if(session.getAttribute(Attributes.SessionAttributes.LOGIN.toString()) != null) {
			((HttpServletResponse) response).sendRedirect(urlDestino);
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		urlDestino = config.getInitParameter("url");
	}
}
