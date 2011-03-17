package ch.paso.address.client;

import ch.paso.address.client.forms.PersonForm;
import ch.paso.address.client.services.IPersonService;
import ch.paso.address.client.services.IPersonServiceAsync;
import ch.paso.address.client.tables.PersonTablePage;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
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
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private final IPersonServiceAsync personService = GWT
			.create(IPersonService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final PersonTablePage table = new PersonTablePage();
		RootPanel.get("nameFieldContainer").add(table);
		table.reload();
		Button newButton = new Button();
		newButton.setText("Neu");
		newButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				PersonForm form = new PersonForm();
				form.addCloseHandler(new CloseHandler<PopupPanel>() {
					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						table.reload();
					}
				});
				form.startNew();
			}
		});
		RootPanel.get("nameFieldContainer").add(newButton);

		}
}
