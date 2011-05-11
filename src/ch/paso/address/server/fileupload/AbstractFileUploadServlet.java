package ch.paso.address.server.fileupload;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * to be used in conjunction with AbstractFileselectorField
 * 
 * @author flwidmer
 * 
 */
public abstract class AbstractFileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 5177386837042777182L;

	private StringBuilder m_errorLog = new StringBuilder();

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		IFileProcessor processor = getConfiguredFileProcessor();
		try {
			ServletFileUpload upload = new ServletFileUpload();
			res.setContentType("text/plain");
			FileItemIterator iterator = upload.getItemIterator(req);
			while (iterator.hasNext()) {
				FileItemStream item = iterator.next();
				InputStream stream = item.openStream();
				if (item.isFormField()) {
					// if item is formfield, add to list of hiddenfields
					String name = item.getFieldName();
					processor.execProcessField(name, stream);

				} else {
					String name = item.getFieldName();
					String filename = item.getName();
					processor.execProcessFile(name, filename, stream);
				}
			}
			processor.execProcessAfter();
			processor.execCreateResponse(res);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	protected abstract IFileProcessor getConfiguredFileProcessor();
}
