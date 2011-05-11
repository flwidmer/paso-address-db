package ch.paso.address.client.forms.fields;

import java.util.List;

import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;

public abstract class AbstractFileselectorField extends
		AbstractLabeledFormField {
	public class HiddenField extends TextBox {
		public HiddenField(String name) {
			super();
			setName(name);
			setVisible(false);
		}
	}

	private FileUpload m_fileUploader;
	private FormPanel m_formPanel;
	private VerticalPanel m_verticalPanel;
	private List<HiddenField> m_hiddenFieldList;

	@Override
	protected void onLoad() {
		super.onLoad();
		m_hiddenFieldList = getConfiguredHiddenFields();
		m_verticalPanel = new VerticalPanel();
		m_fileUploader = new FileUpload();
		m_formPanel = new FormPanel();
		m_formPanel.add(m_verticalPanel);
		m_verticalPanel.add(m_fileUploader);
		addHiddenInformation(m_verticalPanel);
		add(m_formPanel);
		m_fileUploader.setWidth(getConfiguredWidth());
		m_fileUploader.setName(getConfiguredFileUploaderFromFieldName());
		m_formPanel.setMethod(FormPanel.METHOD_POST);
		m_formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		m_formPanel.setAction(getConfiguredUploadAction());
		m_formPanel.addSubmitCompleteHandler(getConfiguredSubmitCompleteHandler());
	}

	private void addHiddenInformation(VerticalPanel verticalPanel) {
		if (m_hiddenFieldList != null) {
			for (HiddenField f : m_hiddenFieldList) {
				verticalPanel.add(f);
			}
		}
	}

	/**
	 * called only once
	 * 
	 * @return
	 */
	protected List<HiddenField> getConfiguredHiddenFields() {
		return null;
	}
	/**
	 * The name of the url to which the file is uploaded.
	 * @return
	 */
	protected abstract String getConfiguredUploadAction();

	protected String getConfiguredFileUploaderFromFieldName() {
		return "uploader";
	}

	protected String getConfiguredWidth() {
		return "300px";
	}

	public String getValue() {
		return m_fileUploader.getFilename();
	}

	/**
	 * submit the form
	 */
	public void submit() {
		m_formPanel.submit();
	}

	protected abstract SubmitCompleteHandler getConfiguredSubmitCompleteHandler();
}
