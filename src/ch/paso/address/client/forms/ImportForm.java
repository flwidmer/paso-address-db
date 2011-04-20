package ch.paso.address.client.forms;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class ImportForm extends AbstractForm {

	@Override
	protected List<Widget> getConfiguredFields() {
		//TODO
		return null;
	}

	@Override
	protected List<Button> getConfiguredButtons() {
		//TODO
		return null;
	}

	public class ImportHandler implements IHandler {

		@Override
		public void execStore() {
			//TODO
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
}
