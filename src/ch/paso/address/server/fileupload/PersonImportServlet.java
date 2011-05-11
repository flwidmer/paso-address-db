package ch.paso.address.server.fileupload;


public class PersonImportServlet extends AbstractFileUploadServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2132902045666475643L;

	@Override
	protected IFileProcessor getConfiguredFileProcessor() {
		return new PersonUploadProcessor();
	}
}
