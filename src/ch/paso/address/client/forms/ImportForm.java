package ch.paso.address.client.forms;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.forms.fields.AbstractFileselectorField;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class ImportForm extends AbstractForm {

	@Override
	protected List<Widget> getConfiguredFields() {
		ArrayList<Widget> fields = new ArrayList<Widget>();
		fields.add(new SelectFile());
		return fields;
	}

	@Override
	protected List<Button> getConfiguredButtons() {
		return new ArrayList<Button>();
	}

	public class ImportHandler implements IHandler {

		@Override
		public void execStore() {
			// TODO
		}

		@Override
		public void execLoad() {
			// TODO
			setTitle("Import");
		}

	}

	public class ExportHandler implements IHandler {

		@Override
		public void execStore() {
			// TODO Auto-generated method stub

		}

		@Override
		public void execLoad() {
			// TODO Auto-generated method stub
			setTitle("Export");
		}

	}

	public void startImport() {
		startHandler(new ImportHandler());
	}

	public void startExport() {
		startHandler(new ExportHandler());
	}

	public class SelectFile extends AbstractFileselectorField {
		@Override
		protected String getConfiguredLabel() {
			return "File";
		}
	}

}
