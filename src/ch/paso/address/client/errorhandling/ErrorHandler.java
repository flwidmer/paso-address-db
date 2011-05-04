package ch.paso.address.client.errorhandling;

import ch.paso.address.client.Ch_paso_address;
import ch.paso.address.client.auth.ClientAuthenticator;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.StatusCodeException;

public class ErrorHandler {

	public static void handleError(Throwable e) {
		if (e instanceof StatusCodeException && ((StatusCodeException)e).getStatusCode()==401) {
			reauthenticate();
		}else{
			Window.alert(e.getMessage());
		}
	}

	public static void handleError(String msg, Throwable e) {
		if (e instanceof StatusCodeException && ((StatusCodeException)e).getStatusCode()==401) {
			reauthenticate();
		}else{
			Window.alert(msg + "\n" + e.getMessage());
		}
	}

	private static void reauthenticate() {
		ClientAuthenticator ca = new ClientAuthenticator();
		ca.getAuthenticationCredentials(new AsyncCallback<String[]>() {

			@Override
			public void onSuccess(String[] result) {
				Ch_paso_address.getNavigation().setLogin(
						Ch_paso_address.getUsername());
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorHandler.handleError(caught);
			}
		});
	}

	public static void handleError(String msg) {
		Window.alert(msg);
	}
}
