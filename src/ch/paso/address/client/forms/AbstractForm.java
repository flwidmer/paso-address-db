package ch.paso.address.client.forms;

import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractForm extends DialogBox {
	private VerticalPanel m_mainPanel;
	private VerticalPanel m_FieldsPanel;
	private HorizontalPanel m_buttonPanel;
	private List<Button> m_buttons;
	private boolean m_initialized = false;
	private IHandler m_handler;

	@Override
	protected void onLoad() {
		init();
		execInit();
		setInitialized(true);
	}

	private void execInit() {
	}

	private void init() {
		setMainPanel(new VerticalPanel());
		setFieldsPanel(new VerticalPanel());
		setButtonPanel(new HorizontalPanel());
		getMainPanel().add(getFieldsPanel());
		getMainPanel().add(getButtonPanel());
		getMainPanel().setCellHorizontalAlignment(getButtonPanel(),
				HasHorizontalAlignment.ALIGN_RIGHT);
		add(getMainPanel());
		setButtonsInternal(getConfiguredButtons());
		getButtonPanel().setHorizontalAlignment(
				HasHorizontalAlignment.ALIGN_RIGHT);
		setFieldsInternal(getConfiguredFields());
		setText(getConfiguredTitle());
	}

	protected String getConfiguredTitle() {
		return "";
	}

	private void setFieldsInternal(List<Widget> fields) {
		getFieldsPanel().clear();
		for (Widget widget : fields) {
			getFieldsPanel().add(widget);
		}
	}

	protected abstract List<Widget> getConfiguredFields();

	private void setButtonsInternal(List<Button> buttonList) {
		getButtonPanel().clear();
		for (Button button : buttonList) {
			getButtonPanel().add(button);
		}
	}

	protected abstract List<Button> getConfiguredButtons();

	public void setMainPanel(VerticalPanel mainPanel) {
		m_mainPanel = mainPanel;
	}

	public VerticalPanel getMainPanel() {
		return m_mainPanel;
	}

	public void setFieldsPanel(VerticalPanel fields) {
		m_FieldsPanel = fields;
	}

	public VerticalPanel getFieldsPanel() {
		return m_FieldsPanel;
	}

	public void setInitialized(boolean initialized) {
		m_initialized = initialized;
	}

	public boolean isInitialized() {
		return m_initialized;
	}

	public void setButtonPanel(HorizontalPanel buttonPanel) {
		m_buttonPanel = buttonPanel;
	}

	public HorizontalPanel getButtonPanel() {
		return m_buttonPanel;
	}

	public void setButtons(List<Button> buttons) {
		m_buttons = buttons;
		setButtonsInternal(buttons);
	}

	public List<Button> getButtons() {
		return m_buttons;
	}

	public abstract void doOk();

	public abstract void doCancel();

	public void store() {
		if (getHandler() != null) {
			getHandler().execStore();
		}
	}

	public void load() {
		if (getHandler() != null) {
			getHandler().execLoad();
		}
	}

	public void setHandler(IHandler handler) {
		m_handler = handler;
	}

	public IHandler getHandler() {
		return m_handler;
	}
	public void startHandler(IHandler handler){
		setHandler(handler);
		load();
		center();
		show();
	}
}
