package ch.paso.address.client.forms;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.forms.PersonForm.DatesGroupbox.ActiveField;
import ch.paso.address.client.forms.PersonForm.DatesGroupbox.EntryDateField;
import ch.paso.address.client.forms.PersonForm.DatesGroupbox.LeftDateField;
import ch.paso.address.client.forms.PersonForm.InfoBox.BirthDateField;
import ch.paso.address.client.forms.PersonForm.InfoBox.FirstNameField;
import ch.paso.address.client.forms.PersonForm.InfoBox.LastNameField;
import ch.paso.address.client.forms.PersonForm.InfoBox.VulgoField;
import ch.paso.address.client.forms.fields.AbstractButton;
import ch.paso.address.client.forms.fields.AbstractCheckboxfield;
import ch.paso.address.client.forms.fields.AbstractDateField;
import ch.paso.address.client.forms.fields.AbstractGroupBox;
import ch.paso.address.client.forms.fields.AbstractTextField;
import ch.paso.address.client.services.IPersonService;
import ch.paso.address.client.services.IPersonServiceAsync;
import ch.paso.address.shared.entities.PersonEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class PersonForm extends AbstractForm {

	private Long m_id;
	private FirstNameField m_firstNameField;
	private LastNameField m_lastNameField;
	private VulgoField m_vulgoField;
	private BirthDateField m_birthDateField;
	private EntryDateField m_entryDateField;
	private LeftDateField m_leftDateField;
	private ActiveField m_activeField;

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

		public class VulgoField extends AbstractTextField {
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
			setFirstNameField(new FirstNameField());
			setLastNameField(new LastNameField());
			setVulgoField(new VulgoField());
			setBirthDateField(new BirthDateField());
			result.add(getFirstNameField());
			result.add(getLastNameField());
			result.add(getVulgoField());
			result.add(getBirthDateField());
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
			setEntryDateField(new EntryDateField());
			setLeftDateField(new LeftDateField());
			setActiveField(new ActiveField());
			result.add(getEntryDateField());
			result.add(getLeftDateField());
			result.add(getActiveField());
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
			PersonEntity p = exportFormData();
			IPersonServiceAsync svc = GWT.create(IPersonService.class);
			svc.storePerson(p, new AsyncCallback<PersonEntity>() {

				@Override
				public void onFailure(Throwable caught) {
					//TODO display failure message
					
				}

				@Override
				public void onSuccess(PersonEntity result) {
					hide();
				}
			});
		}

		@Override
		public void execLoad() {
			//New... do nothing
		}

	}

	public void startNew() {
		startHandler(new NewHandler());
	}

	public class ModifyHandler implements IHandler {

		@Override
		public void execStore() {
			PersonEntity p = exportFormData();
			IPersonServiceAsync svc = GWT.create(IPersonService.class);
			svc.storePerson(p, new AsyncCallback<PersonEntity>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Alert to failure
				}

				@Override
				public void onSuccess(PersonEntity result) {
					hide();
				}
			});
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
		getFirstNameField().setValue(result.getFirstName());
		getLastNameField().setValue(result.getLastName());
		getVulgoField().setValue(result.getVulgo());
		getBirthDateField().setValue(result.getBirthDate());
		getEntryDateField().setValue(result.getEntry());
		getLeftDateField().setValue(result.getLeft());
		getActiveField().setValue(result.isActive());
	}

	private PersonEntity exportFormData() {
		PersonEntity p = new PersonEntity();
		p.setFirstName(getFirstNameField().getValue());
		p.setLastName(getLastNameField().getValue());
		p.setVulgo(getVulgoField().getValue());
		p.setBirthDate(getBirthDateField().getValue());
		p.setEntry(getEntryDateField().getValue());
		p.setLeft(getLeftDateField().getValue());
		p.setActive(getActiveField().getValue());
		return p;
	}

	public void setFirstNameField(FirstNameField firstNameField) {
		m_firstNameField = firstNameField;
	}

	public FirstNameField getFirstNameField() {
		return m_firstNameField;
	}

	public void setLastNameField(LastNameField lastNameField) {
		m_lastNameField = lastNameField;
	}

	public LastNameField getLastNameField() {
		return m_lastNameField;
	}

	public void setVulgoField(VulgoField vulgoField) {
		m_vulgoField = vulgoField;
	}

	public VulgoField getVulgoField() {
		return m_vulgoField;
	}

	public void setBirthDateField(BirthDateField birthDateField) {
		m_birthDateField = birthDateField;
	}

	public BirthDateField getBirthDateField() {
		return m_birthDateField;
	}

	public void setEntryDateField(EntryDateField entryDateField) {
		m_entryDateField = entryDateField;
	}

	public EntryDateField getEntryDateField() {
		return m_entryDateField;
	}

	public void setLeftDateField(LeftDateField leftDateField) {
		m_leftDateField = leftDateField;
	}

	public LeftDateField getLeftDateField() {
		return m_leftDateField;
	}

	public void setActiveField(ActiveField activeField) {
		m_activeField = activeField;
	}

	public ActiveField getActiveField() {
		return m_activeField;
	}
}
