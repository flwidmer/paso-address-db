package ch.paso.address.server.fileupload;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.paso.address.server.services.PersonService;
import ch.paso.address.shared.entities.PersonEntity;

public class PersonExportServlet extends HttpServlet {

	private static final long serialVersionUID = -6764307045576516326L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handleRequest(req, resp);
	}

	private void handleRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// load person table
		PersonService ps = new PersonService();
		List<PersonEntity> personList = ps.getAllPersons();
		// set parameters
		resp.setContentType("text/csv");
		String disposition = "attachment; fileName=data.csv";
		resp.setHeader("Content-Disposition", disposition);
		// write CSV file
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		PrintWriter out = resp.getWriter();
		for (PersonEntity p : personList) {
			out.write(p.getFirstName());
			out.write(";");
			out.write(p.getLastName());
			out.write(";");
			out.write(p.getVulgo());
			out.write(";");
			out.write(p.getStreet());
			out.write(";'");
			out.write(p.getPlz());
			out.write("';");
			out.write(p.getTown());
			out.write(";'");
			out.write(p.getPhone());
			out.write("';'");
			out.write(p.getCell());
			out.write("';");
			out.write(p.getStufe());
			out.write(";");
			out.write(p.getFunction());
			out.write(";");
			out.write(format.format(p.getBirthDate()));
			out.write("\n");
		}
	}
}
