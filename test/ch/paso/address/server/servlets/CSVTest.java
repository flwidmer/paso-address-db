package ch.paso.address.server.servlets;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletResponse;

import org.junit.Test;

import ch.paso.address.server.fileupload.AbstractFileUploadServlet;
import ch.paso.address.server.fileupload.CSVFileProcessor;
import ch.paso.address.server.fileupload.IFileProcessor;

public class CSVTest {

	@Test
	public void testCSVImporter() throws Exception {
		MockUp m = new MockUp();
		ByteArrayInputStream is = new ByteArrayInputStream(
				"test1,\"test2\",test2.1,\"test3\"".getBytes());
		List<List<String>> list = m.files.get(0);
		for (List<String> list2 : list) {
			for (String string : list2) {
				System.out.println(string);
			}
		}
	}

	public class MockUp extends AbstractFileUploadServlet {

		public List<List<List<String>>> files = new ArrayList<List<List<String>>>();


		@Override
		protected IFileProcessor getConfiguredFileProcessor() {
			return new CSVFileProcessor() {
				
				@Override
				public void execCreateResponse(ServletResponse resp) throws IOException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void execProcessCSVFile(String filename, List<List<String>> csvFile) {
					files.add(csvFile);
				}
			};
		}
	}
}
