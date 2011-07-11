package ch.paso.address.client.tables;

import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.forms.ImportForm;
import ch.paso.address.client.forms.fields.AbstractButton;

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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ImportPage extends Composite {
	private VerticalPanel m_panel;

	public ImportPage() {
		setPanel(new VerticalPanel());
		initWidget(getPanel());
	}

	@Override
	protected void onLoad() {
		Anchor importAnchor = new Anchor("Import");
		importAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ImportForm form = new ImportForm();
				form.startImport();
			}
		});

		Anchor exportAnchor = new Anchor("Export");
		exportAnchor.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// create http request to export servlet
				Window.open("/ch_paso_address/auth/personexport", "Export", "_blank");
			}
		});

		getPanel().add(importAnchor);
		getPanel().add(exportAnchor);
	}

	public void setPanel(VerticalPanel panel) {
		m_panel = panel;
	}

	public VerticalPanel getPanel() {
		return m_panel;
	}
}
