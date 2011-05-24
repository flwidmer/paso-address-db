package ch.paso.address.client.forms.fields;

import ch.paso.address.client.tables.AbstractTable;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class AbstractTableField<T> extends VerticalPanel {

	private AbstractTable<T> m_table;
	private VerticalPanel m_vp;
	private Label m_label;

	@Override
	protected void onLoad() {
		m_vp = new VerticalPanel();
		m_table = getConfiguredTable();
		m_label = new Label(getConfiguredLabel());
		getVp().add(m_label);
		getVp().add(m_table);
		add(getVp());
	}

	protected String getConfiguredLabel() {
		return "";
	}

	protected abstract AbstractTable<T> getConfiguredTable();

	public AbstractTable<T> getTheTable() {
		return m_table;
	}

	public VerticalPanel getVp() {
		return m_vp;
	}

	public void setLabel(String label) {
		m_label.setText(label);
	}
}
