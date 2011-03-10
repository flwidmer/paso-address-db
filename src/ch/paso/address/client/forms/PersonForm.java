package ch.paso.address.client.forms;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.forms.fields.AbstractButton;
import ch.paso.address.client.forms.fields.AbstractCheckboxfield;
import ch.paso.address.client.forms.fields.AbstractDateField;
import ch.paso.address.client.forms.fields.AbstractGroupBox;
import ch.paso.address.client.forms.fields.AbstractTextField;
import ch.paso.address.client.services.IPersonService;
import ch.paso.address.client.services.IPersonServiceAsync;
import ch.paso.address.server.services.PersonService;
import ch.paso.address.shared.entities.PersonEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class PersonForm extends AbstractForm {

	private Long m_id;

	@Override
	protected String getConfiguredTitle() {
		return "Person";
	}

	public class InfoBox extends AbstractGroupBox {
		public class FirstNameField extends AbstractTextField {
			@Override
			protected String getConfiguredLabel() {
				return "Vorname";
			}
		}

		public class LastNameField extends AbstractTextField {
			@Override
			protected String getConfiguredLabel() {
				return "Nachname";
			}
		}

		public class VulgoFîeld extends AbstractTextField {
			@Override
			protected String getConfiguredLabel() {
				return "Vulgo";
			}
		}

		public class BirthDateField extends AbstractDateField {
			@Override
			protected String getConfiguredLabel() {
				return "Geburtstag";
			}
		}

		@Override
		protected List<Widget> getConfiguredFields() {
			ArrayList<Widget> result = new ArrayList<Widget>();
			result.add(new FirstNameField());
			result.add(new LastNameField());
			result.add(new VulgoFîeld());
			result.add(new BirthDateField());
			return result;
		}
	}

	public class DatesGroupbox extends AbstractGroupBox {
		public class ActiveField extends AbstractCheckboxfield {
			@Override
			protected String getConfiguredLabel() {
				return "Aktiv";
			}

			@Override
			protected boolean getConfiguredInitValue() {
				return true;
			}
		}

		public class EntryDateField extends AbstractDateField {
			@Override
			protected String getConfiguredLabel() {
				return "Eintritt";
			}
		}

		public class LeftDateField extends AbstractDateField {
			@Override
			protected String getConfiguredLabel() {
				return "Austritt";
			}
		}

		@Override
		protected List<Widget> getConfiguredFields() {
			ArrayList<Widget> result = new ArrayList<Widget>();
			result.add(new EntryDateField());
			result.add(new LeftDateField());
			result.add(new ActiveField());
			return result;
		}
	}

	public class OkButton extends AbstractButton {
		@Override
		protected String getConfiguredLabel() {
			return "Ok";
		}

		@Override
		protected void execClick(ClickEvent event) {
			doOk();
		}
	}

	public class CancelButton extends AbstractButton {
		@Override
		protected String getConfiguredLabel() {
			return "Cancel";
		}

		@Override
		protected void execClick(ClickEvent event) {
			doCancel();
		}
	}

	@Override
	protected List<Widget> getConfiguredFields() {
		List<Widget> result = new ArrayList<Widget>();
		result.add(new InfoBox());
		result.add(new DatesGroupbox());
		return result;
	}

	@Override
	protected List<Button> getConfiguredButtons() {
		List<Button> result = new ArrayList<Button>();
		result.add(new OkButton());
		result.add(new CancelButton());
		return result;
	}

	@Override
	public void doOk() {
		store();
		hide();
	}

	@Override
	public void doCancel() {
		hide();
	}

	public class NewHandler implements IHandler {

		@Override
		public void execStore() {
			// TODO Auto-generated method stub

		}

		@Override
		public void execLoad() {
			// TODO Auto-generated method stub

		}

	}

	public void startNew() {
		startHandler(new NewHandler());
	}

	public class ModifyHandler implements IHandler {

		@Override
		public void execStore() {
			// TODO
		}

		@Override
		public void execLoad() {
			IPersonServiceAsync personService = GWT
					.create(IPersonService.class);
			personService.getPerson(getId(), new AsyncCallback<PersonEntity>() {

				@Override
				public void onSuccess(PersonEntity result) {
					importFormData(result);
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Alert to failure
				}
			});
		}
	}

	public void startModify() {
		startHandler(new ModifyHandler());
	}

	public void setId(Long id) {
		m_id = id;
	}

	public Long getId() {
		return m_id;
	}

	private void importFormData(PersonEntity result) {
		// TODO Auto-generated method stub

	}

	private PersonEntity exportFormData() {
		// TODO
		return null;
	}
}
