package ch.paso.address.client.forms.fields;

import com.google.gwt.user.client.ui.TextBox;

public abstract class AbstractTextField extends AbstractLabeledFormField {

	private TextBox m_textBox;

	@Override
	protected void onLoad() {
		super.onLoad();
		setTextBox(new TextBox());
		getTextBox().setWidth(getConfiguredTextWidth());
		setEnabled(getConfiguredEnabled());
		setValue(getConfiguredInitialValue());
		add(m_textBox);
	}

	protected String getConfiguredTextWidth() {
		return "100px";
	}

	public void setTextBox(TextBox textBox) {
		m_textBox = textBox;
	}

	public TextBox getTextBox() {
		return m_textBox;
	}
	
	public void setEnabled(boolean enabled){
		getTextBox().setEnabled(enabled);
	}
	protected boolean getConfiguredEnabled(){
		return true;
	}
	protected String getConfiguredInitialValue(){
		return null;
	}
	public void setValue(String value) {
		getTextBox().setValue(value);
	}

	public String getValue() {
		return getTextBox().getValue();
	}
}
