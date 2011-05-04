package ch.paso.address.server.auth;

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

public class AuthenticationFilter implements Filter {

	private FilterConfig m_config;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// check auth
		HttpSession session = ((HttpServletRequest) request).getSession();
		Object attribute = session.getAttribute("user");
		if (attribute != null) {
			chain.doFilter(request, response);
		} else {
			// TODO send http error
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "please authenticate");
			//response.getWriter().write("please authenticate");
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.m_config = config;
	}

}
