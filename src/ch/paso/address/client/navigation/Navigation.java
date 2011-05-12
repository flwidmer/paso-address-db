package ch.paso.address.client.navigation;

import org.apache.catalina.HttpRequest;

import ch.paso.address.client.Ch_paso_address;
import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.tables.AdminPage;
import ch.paso.address.client.tables.ImportPage;
import ch.paso.address.client.tables.PersonTablePage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
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
		getPanel().add(new LogoutLink());

	}

	public void setLogin() {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST,
				"/authentication");
		rb.setHeader("Content-Type", "application/x-www-form-urlencoded");
		rb.setRequestData("action=user");
		rb.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				m_loginLabel.setText("Logged in as: " + response.getText());
			}

			@Override
			public void onError(Request request, Throwable exception) {
				ErrorHandler.handleError(exception);
			}
		});
		try {
			rb.send();
		} catch (RequestException e) {
			ErrorHandler.handleError(e);
		}
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

	public class LogoutLink extends Anchor {
		public LogoutLink() {
			setText("Logout");
			setStyleName("NavLink");
			addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Ch_paso_address.logout();
				}
			});
		}

	}

	public void switchTo(Widget target) {
		getRootPanel().clear();
		getRootPanel().add(target);
		m_activeWidget = target;
	}

}
