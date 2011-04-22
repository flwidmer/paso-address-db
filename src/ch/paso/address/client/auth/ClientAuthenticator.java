package ch.paso.address.client.auth;

import ch.paso.address.client.Ch_paso_address;
import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.forms.fields.AbstractButton;
import ch.paso.address.client.forms.fields.AbstractTextField;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ClientAuthenticator {

	private PasswordField m_passwordField;
	private UsernameField m_usernameField;
	private DecoratedPopupPanel m_panel;
	private AsyncCallback<String[]> m_callback;

	public void getAuthenticationCredentials(AsyncCallback<String[]> callback) {
		m_callback = callback;
		m_panel = new DecoratedPopupPanel();
		m_panel.setModal(true);
		m_panel.setGlassEnabled(true);
		VerticalPanel vp = new VerticalPanel();
		m_panel.add(vp);
		m_usernameField = new UsernameField();
		m_passwordField = new PasswordField();
		vp.add(m_usernameField);
		vp.add(m_passwordField);
		HorizontalPanel hp = new HorizontalPanel();
		hp.add(new OkButton());
		hp.add(new CancelButton());
		vp.add(hp);
		m_panel.center();
		m_panel.show();
	}

	private void doAuthentication() {
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,
				"/authentication");
		builder.setHeader("Content-Type", "application/x-www-form-urlencoded");
		builder.setRequestData("action=auth&user=fwi&password=asd");
		builder.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				if (response.getText() != null
						&& response.getText().equals("auth OK")) {
					m_callback.onSuccess(new String[] {
							m_usernameField.getValue(),
							m_passwordField.getValue() });
				} else {
					ErrorHandler.handleError("Authentication not OK");
				}

			}

			@Override
			public void onError(Request request, Throwable exception) {
				Window.alert(exception.getMessage());

			}
		});
		try {
			builder.send();
		} catch (RequestException e) {
			ErrorHandler.handleError("Error authenticating", e);
		}

	}

	private void doOk() {
		doAuthentication();
		m_panel.hide();

	}

	public class UsernameField extends AbstractTextField {
		@Override
		protected String getConfiguredLabel() {
			return "Username";
		}

		@Override
		protected void execEnter() {
			doOk();
		}
	}

	public class PasswordField extends AbstractTextField {
		@Override
		protected String getConfiguredLabel() {
			return "Password";
		}

		@Override
		protected void execEnter() {
			doOk();
		}

		@Override
		protected boolean getConfiguredPasswordField() {
			return true;
		}

	}

	public class OkButton extends AbstractButton {
		@Override
		protected String getConfiguredLabel() {
			return "Ok";
		}

		@Override
		protected void execClick(ClickEvent event) {
			doOk();
		}

	}

	public class CancelButton extends AbstractButton {
		@Override
		protected String getConfiguredLabel() {
			return "Cancel";
		}

		@Override
		protected void execClick(ClickEvent event) {
			m_panel.hide();
			m_callback
					.onFailure(new Throwable("Authentication aborted by user"));
		}
	}
}
