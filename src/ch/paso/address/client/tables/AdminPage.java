package ch.paso.address.client.tables;

import ch.paso.address.client.Ch_paso_address;
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
		getPanel().add(new FunctionAdminLink(Ch_paso_address.getNavigation()));

	}

	public void setPanel(VerticalPanel panel) {
		m_panel = panel;
	}

	public VerticalPanel getPanel() {
		return m_panel;
	}
	
	public class FunctionAdminLink extends AbstractNavigationLink{

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
}
