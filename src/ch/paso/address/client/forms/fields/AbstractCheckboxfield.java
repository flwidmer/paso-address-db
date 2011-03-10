package ch.paso.address.client.forms.fields;

import com.google.gwt.user.client.ui.CheckBox;

public abstract class AbstractCheckboxfield extends AbstractLabeledFormField {
	private CheckBox m_checkbox;

	public void setCheckbox(CheckBox checkbox) {
		m_checkbox = checkbox;
	}

	public CheckBox getCheckbox() {
		return m_checkbox;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		setCheckbox(new CheckBox());
		getCheckbox().setValue(getConfiguredInitValue());
		setEnabled(getConfiguredEnabled());
		setValue(getConfiguredInitValue());
		add(getCheckbox());
	}

	protected boolean getConfiguredInitValue() {
		return false;
	}

	public boolean getValue() {
		return getCheckbox().getValue();
	}

	public void setValue(boolean value) {
		getCheckbox().setValue(value);
	}

	public void setEnabled(boolean enabled) {
		getCheckbox().setEnabled(enabled);
	}

	protected boolean getConfiguredEnabled() {
		return true;
	}
}
