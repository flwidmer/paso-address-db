package ch.paso.address.client.forms;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.forms.fields.AbstractFileselectorField;
import ch.paso.address.client.forms.fields.AbstractTextField;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ImportForm extends AbstractForm {

	private SelectFile m_fileSelector;
	private NumberOfHeaderLinesField m_numberOfHeaderLinesField;

	@Override
	protected String getConfiguredTitle() {
		return "Import";
	}
	@Override
	protected List<Widget> getConfiguredFields() {
		ArrayList<Widget> fields = new ArrayList<Widget>();
		m_fileSelector = new SelectFile();
		m_numberOfHeaderLinesField = new NumberOfHeaderLinesField();
		fields.add(new FormatInformations());
		fields.add(m_numberOfHeaderLinesField);
		fields.add(m_fileSelector);
		return fields;
	}

	public class ImportHandler implements IHandler {

		@Override
		public void execStore() {
			m_fileSelector.getIgnoreNumber().setValue(
					m_numberOfHeaderLinesField.getValue());
			m_fileSelector.submit();
		}

		@Override
		public void execLoad() {
			
		}

	}

	public void startImport() {
		startHandler(new ImportHandler());
	}

	public class FormatInformations extends HTML{
		public FormatInformations() {
			setHTML("Das file muss durch \",\" getrennt folgende Felder enthalten:<br/> " +
					"<ul>" +
					"<li>Vorname</li>" +
					"<li>LastName</li>" +
					"<li>Vulgo</li>" +
					"<li>Strasse</li>" +
					"<li>PLZ</li>" +
					"<li>Ort</li>" +
					"<li>Telefon</li>" +
					"<li>Mobile</li>" +
					"<li>Email</li>" +
					"<li>Geburtstag (dd.mm.yyyy)</li>" +
					"<li>Eintritt (dd.mm.yyyy)</li>" +
					"<li>Stufe</li>" +
					"<li>Funktion</li>" +
					"</ul>" +
					"Stufe und Funktion sollten so geschrieben sein, wie in der Anzeige.");
		}
	}

	public class NumberOfHeaderLinesField extends AbstractTextField {
		@Override
		protected String getConfiguredLabel() {
			return "Anzahl zeilen ignorieren";
		}

		@Override
		protected String getConfiguredInitialValue() {
			return "0";
		}
		@Override
		protected String getConfiguredLabelWidth() {
			return "150px";
		}
	}

	public class SelectFile extends AbstractFileselectorField {
		private HiddenField m_ignoreNumber;

		@Override
		protected String getConfiguredLabel() {
			return "File";
		}

		@Override
		protected List<HiddenField> getConfiguredHiddenFields() {
			ArrayList<HiddenField> result = new ArrayList<AbstractFileselectorField.HiddenField>();
			setIgnoreNumber(new HiddenField("ignoreNumber"));
			result.add(getIgnoreNumber());
			return result;
		}

		@Override
		protected String getConfiguredUploadAction() {
			return "/ch_paso_address/auth/personimport";
		}

		@Override
		protected SubmitCompleteHandler getConfiguredSubmitCompleteHandler() {
			return new SubmitCompleteHandler() {

				@Override
				public void onSubmitComplete(SubmitCompleteEvent event) {
					String results = event.getResults();
					if (results == null || results.isEmpty()) {
						hide();
					} else {
						Window.alert(results);
						hide();
					}
				}
			};
		}

		public void setIgnoreNumber(HiddenField ignoreNumber) {
			m_ignoreNumber = ignoreNumber;
		}

		public HiddenField getIgnoreNumber() {
			return m_ignoreNumber;
		}
	}

}
