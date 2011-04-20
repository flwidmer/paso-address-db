package ch.paso.address.client.errorhandling;

import com.google.gwt.user.client.Window;

public class Errorhandler {

	public void handleError(Throwable e) {
		Window.alert(e.getMessage());
	}

	public static void handleError(String msg, Throwable e) {
		Window.alert(msg + "\n" + e.getMessage());
	}

	public static void handleError(String msg) {
		Window.alert(msg);
	}
}
