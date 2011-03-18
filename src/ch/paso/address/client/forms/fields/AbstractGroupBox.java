package ch.paso.address.client.forms.fields;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractGroupBox extends VerticalPanel {

	private List<VerticalPanel> m_panels;
	private List<Widget> m_fields;
	private int m_numberofColumns;
	private boolean m_initialized = false;
	private int m_border;
	private HorizontalPanel m_innerPanel;
	private Label m_label;

	public AbstractGroupBox() {
		setPanels(new ArrayList<VerticalPanel>());
	}

	@Override
	protected void onLoad() {
		init();
		execInit();
		setInitialized(true);
	}

	private void init() {
		setInnerPanel(new HorizontalPanel());
		setLabel(new HTML());
		getLabel().setText(getConfiguredLabel());
		getLabel().setStyleName("GroupBoxLabel");
		add(getLabel());
		add(getInnerPanel());
		setBorder(getConfiguredBorder());
		setBorderInternal();
		getInnerPanel().setSpacing(3);
		setNumberofColumns(getConfiguredNumberOfColumns());
		for (int i = 0; i < getNumberofColumns(); i++) {
			VerticalPanel vp = new VerticalPanel();
			getInnerPanel().add(vp);
			getPanels().add(vp);
		}
		setFields(getConfiguredFields());
		assignFieldsInternal();
	}

	protected String getConfiguredLabel() {
		return "";
	}

	protected void assignFieldsInternal() {
		double col = getNumberofColumns();
		double fields = getFields().size();
		int perCol = (int) Math.ceil(fields / col);
		Iterator<Widget> iterator = getFields().iterator();
		for (int i = 0; i < col; i++) {
			for (int k = 0; k < perCol; k++) {
				if (iterator.hasNext()) {
					getPanels().get(i).add(iterator.next());
				}
			}
		}
	}

	protected void execInit() {

	}

	protected abstract List<Widget> getConfiguredFields();

	protected int getConfiguredNumberOfColumns() {
		return 2;
	}

	public void setNumberofColumns(int numberofColumns) {
		m_numberofColumns = numberofColumns;
	}

	public int getNumberofColumns() {
		return m_numberofColumns;
	}

	public void setFields(List<Widget> fields) {
		m_fields = fields;
		if (isInitialized()) {
			assignFieldsInternal();
		}
	}

	public List<Widget> getFields() {
		return m_fields;
	}

	public void setPanels(List<VerticalPanel> panels) {
		m_panels = panels;
	}

	public List<VerticalPanel> getPanels() {
		return m_panels;
	}

	public void setInitialized(boolean initialized) {
		m_initialized = initialized;
	}

	public boolean isInitialized() {
		return m_initialized;
	}

	public void setBorder(int border) {
		m_border = border;
		if (isInitialized()) {
			setBorderInternal();
		}
	}

	private void setBorderInternal() {
		setSpacing(getBorder());
	}

	public int getBorder() {
		return m_border;
	}

	protected int getConfiguredBorder() {
		return 3;
	}

	public void setInnerPanel(HorizontalPanel innerPanel) {
		m_innerPanel = innerPanel;
	}

	public HorizontalPanel getInnerPanel() {
		return m_innerPanel;
	}

	public void setLabel(Label label) {
		m_label = label;
	}

	public Label getLabel() {
		return m_label;
	}
}
