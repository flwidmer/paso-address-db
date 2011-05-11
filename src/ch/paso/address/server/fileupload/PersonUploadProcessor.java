package ch.paso.address.server.fileupload;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import ch.paso.address.server.services.PersonService;
import ch.paso.address.shared.entities.PersonEntity;

public class PersonUploadProcessor extends CSVFileProcessor {

	public void execCreateResponse(ServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		String errorLog = getErrorLog();
		if (errorLog.isEmpty()) {
			// TODO more verbose
			writer.append("OK");
		} else {
			writer.append(errorLog);
		}
	}

	private void parseLine(List<String> line) {
		PersonEntity p = new PersonEntity();
		Iterator<String> iterator = line.iterator();
		// Vorname
		if (iterator.hasNext()) {
			p.setFirstName(iterator.next());
		}
		// LastName
		if (iterator.hasNext()) {
			p.setLastName(iterator.next());
		}
		// Vulgo
		if (iterator.hasNext()) {
			p.setVulgo(iterator.next());
		}
		// Strasse
		if (iterator.hasNext()) {
			p.setStreet(iterator.next());
		}
		// PLZ
		if (iterator.hasNext()) {
			p.setPlz(iterator.next());
		}
		// Ort
		if (iterator.hasNext()) {
			p.setTown(iterator.next());
		}
		// Telefon
		if (iterator.hasNext()) {
			p.setPhone(iterator.next());
		}
		// Mobile
		if (iterator.hasNext()) {
			p.setCell(iterator.next());
		}
		// Email
		if (iterator.hasNext()) {
			p.setEmail(iterator.next());
		}
		// Geburtstag
		if (iterator.hasNext()) {
			SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
			try {
				p.setBirthDate(format.parse(iterator.next()));
			} catch (ParseException e) {
				logError("could not parse birthdate for " + p.getLastName()
						+ ", " + p.getFirstName() + "\n");
			}
		}
		// Entry
		if (iterator.hasNext()) {
			SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
			try {
				p.setEntry(format.parse(iterator.next()));
			} catch (ParseException e) {
				logError("could not parse entrydate " + p.getLastName() + ", "
						+ p.getFirstName() + "\n");
			}
		}
		// Stufe
		if (iterator.hasNext()) {
			p.setStufe(iterator.next());
		}
		// Funktion
		if (iterator.hasNext()) {
			p.setFunction(iterator.next());
		}
		PersonService svc = new PersonService();
		// TODO duplicate check
		svc.storePerson(p);
	}

	private int getIgnoreNumber() {
		List<String[]> hiddenFields = getHiddenFields();
		for (String[] strings : hiddenFields) {
			if (strings[0].equals("ignoreNumber")) {
				return Integer.parseInt(strings[1]);
			}
		}
		return 0;
	}

	public void execProcessCSVFile(String filename, List<List<String>> csvFile) {
		Iterator<List<String>> iterator = csvFile.iterator();
		for (int i = 0; i < getIgnoreNumber(); i++) {
			if (iterator.hasNext()) {
				iterator.next();
			}
		}
		while (iterator.hasNext()) {
			// parseLine
			parseLine(iterator.next());
		}
	}

}
