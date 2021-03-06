package ch.paso.address.client;

import java.util.HashMap;
import java.util.List;

import ch.paso.address.client.auth.ClientAuthenticator;
import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.navigation.Navigation;
import ch.paso.address.client.services.ICodeService;
import ch.paso.address.client.services.ICodeServiceAsync;
import ch.paso.address.client.services.IPermissionService;
import ch.paso.address.client.services.IPermissionServiceAsync;
import ch.paso.address.shared.entities.ICodeType;
import ch.paso.address.shared.entities.StufeCodeType;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ch_paso_address implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	private static String username = "";
	private static String password = "";

	private static Navigation S_navigation;
	private static RootPanel m_rootPanel;
	private static HashMap<String, Integer> m_permissionCache;

	public void onModuleLoad() {
		ICodeServiceAsync svc = GWT.create(ICodeService.class);
		svc.loadActiveCodes(new StufeCodeType(),
				new AsyncCallback<List<ICodeType>>() {

					public void onFailure(Throwable caught) {
						authenticate();
					}

					public void onSuccess(List<ICodeType> result) {
						loadPermissions();

					}
				});
	}

	private void authenticate() {
		ClientAuthenticator ca = new ClientAuthenticator();
		ca.getAuthenticationCredentials(new AsyncCallback<String[]>() {

			@Override
			public void onSuccess(String[] result) {
				setUsername(result[0]);
				setPassword(result[1]);
				loadPermissions();
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorHandler.handleError("Error during initial authentication",
						caught);
			}
		});

	}
	private void display() {
		RootPanel rootPanel = RootPanel.get("display");
		m_rootPanel = rootPanel;
		ScrollPanel sp = new ScrollPanel();
		rootPanel.add(sp);
		Navigation navigation = new Navigation(sp);
		setNavigation(navigation);
		getNavigation().setLogin();
		RootPanel.get("navigation").add(navigation);
	}

	public static void setNavigation(Navigation s_navigation) {
		S_navigation = s_navigation;
	}

	public static Navigation getNavigation() {
		return S_navigation;
	}

	public static void setUsername(String username) {
		Ch_paso_address.username = username;
	}

	public static String getUsername() {
		return username;
	}

	public static void setPassword(String password) {
		Ch_paso_address.password = password;
	}

	public static String getPassword() {
		return password;
	}

	public static void logout() {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET,
				"/authentication?action=logout");
		rb.setCallback(new RequestCallback() {

			@Override
			public void onResponseReceived(Request request, Response response) {
				Window.alert("Log out erfolgreich");
				m_rootPanel.clear();
				getNavigation().getPanel().clear();
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

	private void loadPermissions() {
		IPermissionServiceAsync svc = GWT.create(IPermissionService.class);
		svc.loadPermissions(new AsyncCallback<List<Permission>>() {

			@Override
			public void onFailure(Throwable caught) {
				ErrorHandler.handleError("error loading permissions", caught);
			}

			@Override
			public void onSuccess(List<Permission> result) {
				m_permissionCache = new HashMap<String, Integer>();
				if (result != null) {
					for (Permission p : result) {
						m_permissionCache.put(p.getName(), p.getLevel());
					}
				}
				display();
			}
		});
	}

	public static boolean checkPermission(Permission reqPerm) {
		if (reqPerm == null) {
			return true;
		}
		if (m_permissionCache.containsKey(reqPerm.getName())) {
			Integer level = m_permissionCache.get(reqPerm.getName());
			if (level >= reqPerm.getLevel()) {
				return true;
			}
		}
		return false;
	}
}
