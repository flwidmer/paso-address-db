package ch.paso.address.client.forms.fields;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public abstract class AbstractLabeledFormField extends HorizontalPanel {

	private Label m_label;

	@Override
	protected void onLoad() {
		setLabel(new Label(getConfiguredLabel()));
		getLabel().setWidth(getConfiguredLabelWidth());
		add(getLabel());
	}

	protected String getConfiguredLabelWidth() {
		return "75px";
	}

	protected String getConfiguredLabel() {
		return null;
	}

	public void setLabel(Label label) {
		m_label = label;
	}

	public Label getLabel() {
		return m_label;
	}

}
