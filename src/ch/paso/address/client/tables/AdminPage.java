package ch.paso.address.client.tables;

import ch.paso.address.client.Ch_paso_address;
import ch.paso.address.client.forms.UserForm;
import ch.paso.address.client.navigation.AbstractNavigationLink;
import ch.paso.address.client.navigation.Navigation;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminPage extends Composite {
	private VerticalPanel m_panel;

	public AdminPage() {
		setPanel(new VerticalPanel());
		initWidget(getPanel());
	}

	@Override
	protected void onLoad() {
		Navigation navigation = Ch_paso_address.getNavigation();
		getPanel().add(new FunctionAdminLink(navigation));
		getPanel().add(new StufeAdminLink(navigation));
		getPanel().add(new PermissionAdminLink(navigation));
		getPanel().add(new UserAdminLink(navigation));
	}

	public void setPanel(VerticalPanel panel) {
		m_panel = panel;
	}

	public VerticalPanel getPanel() {
		return m_panel;
	}

	public class FunctionAdminLink extends AbstractNavigationLink {

		public FunctionAdminLink(Navigation n) {
			super(n);
		}

		@Override
		protected String getConfiguredText() {
			return "Funktionen";
		}

		@Override
		protected Widget getConfiguredTarget() {
			return new FunctionAdminPage();
		}

	}

	public class StufeAdminLink extends AbstractNavigationLink {

		public StufeAdminLink(Navigation n) {
			super(n);
		}

		@Override
		protected String getConfiguredText() {
			return "Stufen";
		}

		@Override
		protected Widget getConfiguredTarget() {
			return new StufeAdminPage();
		}
	}

	public class PermissionAdminLink extends AbstractNavigationLink {

		public PermissionAdminLink(Navigation n) {
			super(n);
		}

		@Override
		protected String getConfiguredText() {
			return "Berechtigungen";
		}

		@Override
		protected Widget getConfiguredTarget() {
			return new PermissionAdminPage();
		}

	}

	public class UserAdminLink extends AbstractNavigationLink {

		public UserAdminLink(Navigation n) {
			super(n);
		}

		@Override
		protected String getConfiguredText() {
			return "Benutzer";
		}

		@Override
		protected Widget getConfiguredTarget() {
			UserForm userForm = new UserForm();
			userForm.startNew();
			return new PersonTablePage();
		}

	}
}
