package ch.paso.address.client.forms.fields;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.google.gwt.user.client.ui.FileUpload;

public abstract class AbstractFileselectorField extends AbstractLabeledFormField{
	private FileUpload m_fileUploader;
	
	
	@Override
	protected void onLoad() {
		super.onLoad();
		m_fileUploader = new FileUpload();
		add(m_fileUploader);
		m_fileUploader.setWidth(getConfiguredWidth());
	}

	protected String getConfiguredWidth() {
		return "300px";
	}
	
	public void getValue() {
		String filename = m_fileUploader.getFilename();
	}
	
}
