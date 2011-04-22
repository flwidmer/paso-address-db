package ch.paso.address.client.forms.fields;

import com.google.gwt.event.dom.client.DomEvent.Type;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

public abstract class AbstractTextField extends AbstractLabeledFormField {

	private TextBox m_textBox;

	@Override
	protected void onLoad() {
		super.onLoad();
		if (getConfiguredPasswordField()) {
			setTextBox(new PasswordTextBox());
		} else {
			setTextBox(new TextBox());
		}
		getTextBox().setWidth(getConfiguredTextWidth());
		setEnabled(getConfiguredEnabled());
		setValue(getConfiguredInitialValue());
		add(m_textBox);
		m_textBox.addKeyPressHandler(new KeyPressHandler() {

			private Type<KeyPressHandler> m_type;

			@Override
			public void onKeyPress(KeyPressEvent event) {
				int c = event.getNativeEvent().getKeyCode();
				if (c == KeyCodes.KEY_ENTER) {
					execEnter();
				}
			}
		});
	}

	protected void execEnter() {
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

	public void setEnabled(boolean enabled) {
		getTextBox().setEnabled(enabled);
	}

	protected boolean getConfiguredEnabled() {
		return true;
	}

	protected String getConfiguredInitialValue() {
		return null;
	}

	public void setValue(String value) {
		getTextBox().setValue(value);
	}

	public String getValue() {
		return getTextBox().getValue();
	}

	protected boolean getConfiguredPasswordField() {
		return false;
	}
}
