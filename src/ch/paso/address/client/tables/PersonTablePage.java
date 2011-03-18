package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.paso.address.client.forms.PersonForm;
import ch.paso.address.client.forms.fields.AbstractDateField;
import ch.paso.address.client.services.IPersonService;
import ch.paso.address.client.services.IPersonServiceAsync;
import ch.paso.address.client.tables.columns.AbstractColumn;
import ch.paso.address.client.tables.columns.AbstractDateColumn;
import ch.paso.address.client.tables.columns.AbstractStringColumn;
import ch.paso.address.shared.entities.PersonEntity;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;

public class PersonTablePage extends Composite {

	private CellTable<PersonEntity> m_theTable;

	public PersonTablePage() {
		m_theTable = new PersonTable();

		initWidget(m_theTable);
	}

	@Override
	protected void onLoad() {
		reload();
	}

	public void reload() {
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

	public class PersonTable extends AbstractTable<PersonEntity> {

		@Override
		protected List<AbstractColumn> getConfiguredColumns() {
			ArrayList<AbstractColumn> cols = new ArrayList<AbstractColumn>();
			cols.add(new FirstNameColumn());
			cols.add(new LastNameColumn());
			cols.add(new VulgoColumn());
			cols.add(new StreetColumn());
			cols.add(new PLZColumn());
			cols.add(new TownColumn());
			cols.add(new PhoneCoumn());
			cols.add(new CellColumn());
			cols.add(new EmailColumn());
			cols.add(new BirthdayColumn());
			cols.add(new EditButtonColumn());
			cols.add(new DeleteButtonColumn());
			return cols;
		}

		public class FirstNameColumn extends AbstractStringColumn<PersonEntity> {

			@Override
			public String getValue(PersonEntity object) {
				return object.getFirstName();
			}

			@Override
			public String getConfiguredTitle() {
				return "Vorname";
			}
		}

		public class LastNameColumn extends AbstractStringColumn<PersonEntity> {

			@Override
			public String getValue(PersonEntity object) {
				return object.getLastName();
			}

			@Override
			public String getConfiguredTitle() {
				return "LastName";
			}
		}

		public class VulgoColumn extends AbstractStringColumn<PersonEntity> {
			@Override
			public String getValue(PersonEntity object) {
				return object.getVulgo();
			}

			@Override
			public String getConfiguredTitle() {
				return "Vulgo";
			}
		}

		public class StreetColumn extends AbstractStringColumn<PersonEntity> {
			@Override
			public String getValue(PersonEntity object) {
				return object.getStreet();
			}

			@Override
			public String getConfiguredTitle() {
				return "Strasse";
			}
		}

		public class PLZColumn extends AbstractStringColumn<PersonEntity> {
			@Override
			public String getValue(PersonEntity object) {
				return object.getPlz();
			}

			@Override
			public String getConfiguredTitle() {
				return "PLZ";
			}
		}

		public class TownColumn extends AbstractStringColumn<PersonEntity> {
			public String getValue(PersonEntity object) {
				return object.getTown();
			};

			@Override
			public String getConfiguredTitle() {
				return "Ort";
			}
		}

		public class PhoneCoumn extends AbstractStringColumn<PersonEntity> {
			@Override
			public String getValue(PersonEntity object) {
				return object.getPhone();
			}

			@Override
			public String getConfiguredTitle() {
				return "Telefon";
			}
		}

		public class CellColumn extends AbstractStringColumn<PersonEntity> {
			@Override
			public String getValue(PersonEntity object) {
				return object.getCell();
			}

			@Override
			public String getConfiguredTitle() {
				return "Mobile";
			}
		}

		public class EmailColumn extends AbstractStringColumn<PersonEntity> {
			@Override
			public String getValue(PersonEntity object) {
				return object.getEmail();
			}

			@Override
			public String getConfiguredTitle() {
				return "Email";
			}
		}

		public class BirthdayColumn extends AbstractDateColumn<PersonEntity> {
			@Override
			public Date getValue(PersonEntity object) {
				return object.getBirthDate();
			}

			@Override
			public String getConfiguredTitle() {
				return "Geburtstag";
			}
		}

		public class EditButtonColumn extends
				AbstractColumn<PersonEntity, String> {

			public EditButtonColumn() {
				super(new ButtonCell());
				setFieldUpdater(new FieldUpdater<PersonEntity, String>() {

					@Override
					public void update(int index, PersonEntity object,
							String value) {
						PersonForm form = new PersonForm();
						form.addCloseHandler(new CloseHandler<PopupPanel>() {
							@Override
							public void onClose(CloseEvent<PopupPanel> event) {
								reload();
							}
						});
						form.setId(object.getId());
						form.startModify();

					}
				});
			}

			@Override
			public String getValue(PersonEntity object) {
				return "Bearbeiten";
			}

		}

		public class DeleteButtonColumn extends
				AbstractColumn<PersonEntity, String> {

			public DeleteButtonColumn() {
				super(new ButtonCell());
				setFieldUpdater(new FieldUpdater<PersonEntity, String>() {

					@Override
					public void update(final int index, PersonEntity object,
							String value) {
						
						IPersonServiceAsync svc = GWT.create(IPersonService.class);
						svc.deletePerson(object.getId(), new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(Void result) {
								reload();
							}
						});
					}
				});
			}

			@Override
			public String getValue(PersonEntity object) {
				return "Löschen";
			}

		}

	}
}