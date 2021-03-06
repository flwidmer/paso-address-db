package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.forms.PersonForm;
import ch.paso.address.client.services.IPersonService;
import ch.paso.address.client.services.IPersonServiceAsync;
import ch.paso.address.client.tables.columns.AbstractButtonColumn;
import ch.paso.address.client.tables.columns.AbstractColumn;
import ch.paso.address.client.tables.columns.AbstractDateColumn;
import ch.paso.address.client.tables.columns.AbstractStringColumn;
import ch.paso.address.shared.entities.PersonEntity;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;

public class PersonTablePage extends AbstractTablePage<PersonEntity> {

	public PersonTablePage() {
		super();
	}

	@Override
	protected void execNewClick() {
		PersonForm form = new PersonForm();
		form.addCloseHandler(new CloseHandler<PopupPanel>() {
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				reload();
			}
		});
		form.startNew();
	}

	@Override
	protected Permission getConfiguredNewButtonPermission() {
		return new Permission("EditPerson", 100);
	}
	@Override
	protected void reload() {
		IPersonServiceAsync svc = GWT.create(IPersonService.class);
		svc.getAllPersons(new AsyncCallback<List<PersonEntity>>() {

			@Override
			public void onSuccess(List<PersonEntity> result) {
				getTheTable().setRowData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorHandler.handleError("Error during reloading", caught);
			}
		});
	}

	@Override
	protected boolean getConfiguredNewButtonVisible() {
		return true;
	}

	public class PersonTable extends AbstractTable<PersonEntity> {

		@Override
		protected List<AbstractColumn<PersonEntity, ?>> getConfiguredColumns() {
			ArrayList<AbstractColumn<PersonEntity, ?>> cols = new ArrayList<AbstractColumn<PersonEntity, ?>>();
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
			cols.add(new StufeColumn());
			cols.add(new FunctionColumn());
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
				return "Nachname";
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

		public class StufeColumn extends AbstractStringColumn<PersonEntity> {
			@Override
			public String getConfiguredTitle() {
				return "Stufe";
			}

			@Override
			public String getValue(PersonEntity object) {
				return object.getStufe();
			}
		}

		public class FunctionColumn extends AbstractStringColumn<PersonEntity> {
			@Override
			public String getConfiguredTitle() {
				return "Funktion";
			}

			@Override
			public String getValue(PersonEntity object) {
				return object.getFunction();
			}
		}

		public class EditButtonColumn extends
				AbstractButtonColumn<PersonEntity> {

			@Override
			public String getValue(PersonEntity object) {
				return "Bearbeiten";
			}

			@Override
			protected Permission getConfiguredPermission() {
				return new Permission("EditPerson", 100);
			}
			@Override
			protected void execOnClick(int index, PersonEntity object,
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

		}

		public class DeleteButtonColumn extends
				AbstractButtonColumn<PersonEntity> {

			@Override
			protected Permission getConfiguredPermission() {
				return new Permission("DeletePerson", 100);
			}

			@Override
			protected void execOnClick(int index, PersonEntity object,
					String value) {
				IPersonServiceAsync svc = GWT.create(IPersonService.class);
				svc.deletePerson(object.getId(), new AsyncCallback<Void>() {
					@Override
					public void onFailure(Throwable caught) {
						ErrorHandler.handleError("Error during deleting",
								caught);
					}

					@Override
					public void onSuccess(Void result) {
						reload();
					}

				});

			}

			@Override
			protected String getConfiguredLabel() {
				return "Löschen";
			}
		}

	}

	@Override
	protected CellTable<PersonEntity> getConfiguredTable() {
		return new PersonTable();
	}
}