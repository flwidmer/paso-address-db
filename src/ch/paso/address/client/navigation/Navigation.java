package ch.paso.address.client.navigation;

import ch.paso.address.client.tables.AdminPage;
import ch.paso.address.client.tables.ImportPage;
import ch.paso.address.client.tables.PersonTablePage;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Navigation extends Composite {

	private VerticalPanel m_panel;
	private Panel m_rootPanel;
	private Widget m_activeWidget;
	private Label m_loginLabel = new Label();

	public Navigation(Panel rootPanel) {
		setPanel(new VerticalPanel());
		initWidget(getPanel());
		setRootPanel(rootPanel);
	}

	@Override
	protected void onLoad() {
		getRootPanel().add(new PersonTablePage());
		initNavigation();
		getPanel().add(new PersonTableLink(this));
		getPanel().add(new ImportLink(this));
		getPanel().add(new AdminLink(this));
		getPanel().add(m_loginLabel);
	}

	public void setLogin(String name) {
		m_loginLabel.setText("Logged in as: " + name);
	}

	private void initNavigation() {

	}

	public void setPanel(VerticalPanel panel) {
		m_panel = panel;
	}

	public VerticalPanel getPanel() {
		return m_panel;
	}

	public void setRootPanel(Panel rootPanel) {
		m_rootPanel = rootPanel;
	}

	public Panel getRootPanel() {
		return m_rootPanel;
	}

	public class PersonTableLink extends AbstractNavigationLink {

		public PersonTableLink(Navigation n) {
			super(n);
		}

		@Override
		protected String getConfiguredText() {
			return "Personen";
		}

		@Override
		protected Widget getConfiguredTarget() {
			return new PersonTablePage();
		}

	}

	public class ImportLink extends AbstractNavigationLink {

		public ImportLink(Navigation n) {
			super(n);
		}

		@Override
		protected String getConfiguredText() {
			return "Import / Export";
		}

		@Override
		protected Widget getConfiguredTarget() {
			return new ImportPage();
		}

	}

	public class AdminLink extends AbstractNavigationLink {

		public AdminLink(Navigation n) {
			super(n);
		}

		@Override
		protected String getConfiguredText() {
			return "Admin";
		}

		@Override
		protected Widget getConfiguredTarget() {
			return new AdminPage();
		}

	}

	public void switchTo(Widget target) {
		getRootPanel().clear();
		getRootPanel().add(target);
		m_activeWidget = target;
	}

}
