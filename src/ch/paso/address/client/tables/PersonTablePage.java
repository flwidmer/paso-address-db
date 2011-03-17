package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.forms.PersonForm;
import ch.paso.address.client.services.IPersonService;
import ch.paso.address.client.services.IPersonServiceAsync;
import ch.paso.address.shared.entities.PersonEntity;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;

public class PersonTablePage extends Composite {

	private CellTable<PersonEntity> m_theTable;

	public PersonTablePage() {
		m_theTable = new PersonTable();

		initWidget(m_theTable);
	}

	@Override
	protected void onLoad() {
		IPersonServiceAsync svc = GWT.create(IPersonService.class);
		svc.getAllPersons(new AsyncCallback<List<PersonEntity>>() {
			
			@Override
			public void onSuccess(List<PersonEntity> result) {
				m_theTable.setRowData(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public class PersonTable extends CellTable<PersonEntity> {
		public PersonTable() {
			addColumn(new FirstNameColumn());
			addColumn(new LastNameColumn());
			addColumn(new VulgoColumn());
			addColumn(new EditButtonColumn());
		}

		public class FirstNameColumn extends Column<PersonEntity, String> {

			public FirstNameColumn() {
				super(new TextCell());
			}

			@Override
			public String getValue(PersonEntity object) {
				return object.getFirstName();
			}
		}

		public class LastNameColumn extends Column<PersonEntity, String> {

			public LastNameColumn() {
				super(new TextCell());
			}

			@Override
			public String getValue(PersonEntity object) {
				return object.getLastName();
			}

		}

		public class VulgoColumn extends Column<PersonEntity, String> {
			public VulgoColumn() {
				super(new TextCell());
			}

			@Override
			public String getValue(PersonEntity object) {
				return object.getVulgo();
			}
		}

		public class EditButtonColumn extends Column<PersonEntity, String> {

			public EditButtonColumn() {
				super(new ButtonCell());
				setFieldUpdater(new FieldUpdater<PersonEntity, String>() {

					@Override
					public void update(int index, PersonEntity object,
							String value) {
						PersonForm form = new PersonForm();
						form.startNew();
					}
				});
			}

			@Override
			public String getValue(PersonEntity object) {
				return "Bearbeiten";
			}

		}

	}

}