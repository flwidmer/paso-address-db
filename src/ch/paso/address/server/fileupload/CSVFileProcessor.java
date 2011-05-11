package ch.paso.address.server.fileupload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.bcel.internal.generic.NEW;

public abstract class CSVFileProcessor implements IFileProcessor {
	private List<String[]> m_hiddenFields = new ArrayList<String[]>();
	static final Pattern S_REGEX = Pattern
			.compile("((^\"?+)|(\"?+))([^\",]*)((\"?+,)|(\"?+$))");

	private List<List<List<String>>> m_csvFiles = new LinkedList<List<List<String>>>();
	private List<String> m_filenames = new ArrayList<String>();

	private StringBuilder m_errorLog = new StringBuilder();

	public void execProcessAfter() {
		int i = 0;
		for (List<List<String>> file : getCsvFiles()) {
			execProcessCSVFile(getFilenames().get(i), file);
			i++;
		}
	}

	public void execProcessField(String name, InputStream is)
			throws IOException {
		String[] data = new String[2];
		data[0] = name;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		data[1] = br.readLine();
		getHiddenFields().add(data);
	}

	public void execProcessFile(String name, String filename, InputStream is)
			throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line;
		List<List<String>> result = new LinkedList<List<String>>();
		while ((line = br.readLine()) != null) {
			ArrayList<String> parsed = new ArrayList<String>();
			Matcher m = S_REGEX.matcher(line);
			if (m.find()) {
				do {
					String match = m.group(4);
					parsed.add(match);
				} while (m.find());
			}
			result.add(parsed);
		}
		getCsvFiles().add(result);
		getFilenames().add(filename);
	}

	public List<String> getFilenames() {
		return m_filenames;
	}

	public List<List<List<String>>> getCsvFiles() {
		return m_csvFiles;
	}

	public List<String[]> getHiddenFields() {
		return m_hiddenFields;
	}

	public void logError(String string) {
		m_errorLog.append(string);
	}

	public String getErrorLog() {
		return m_errorLog.toString();
	}

	public abstract void execProcessCSVFile(String filename,
			List<List<String>> csvFile);
	
}