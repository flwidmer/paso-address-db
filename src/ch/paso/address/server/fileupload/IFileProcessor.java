package ch.paso.address.server.fileupload;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletResponse;

public interface IFileProcessor {
	/**
	 * This method is called with Field data. Process fields inside this method.
	 */
	public void execProcessField(String name, InputStream is)
			throws IOException;

	/**
	 * This method is called with File data. Process files with this method
	 */
	public void execProcessFile(String name, String filename, InputStream is)
			throws IOException;

	/**
	 * This method is called, after all data has been read. If you need all
	 * data, before you start processing, use this mthod.
	 */
	public void execProcessAfter();

	public void execCreateResponse(ServletResponse resp) throws IOException;
	
	public void logError(String string);
}
