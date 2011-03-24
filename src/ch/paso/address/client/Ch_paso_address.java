package ch.paso.address.client;

import ch.paso.address.client.navigation.Navigation;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Ch_paso_address implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * This is the entry point method.
	 */
	
	private static Navigation S_navigation;
	public void onModuleLoad() {
		Navigation navigation = new Navigation(RootPanel.get("display"));
		setNavigation(navigation);
		RootPanel.get("navigation").add(navigation);
	}
	public static void setNavigation(Navigation s_navigation) {
		S_navigation = s_navigation;
	}
	public static Navigation getNavigation() {
		return S_navigation;
	}
}
