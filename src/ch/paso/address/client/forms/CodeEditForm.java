package ch.paso.address.client.forms;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.forms.CodeEditForm.MainBox.ActiveField;
import ch.paso.address.client.forms.CodeEditForm.MainBox.ValueField;
import ch.paso.address.client.forms.fields.AbstractButton;
import ch.paso.address.client.forms.fields.AbstractCheckboxfield;
import ch.paso.address.client.forms.fields.AbstractGroupBox;
import ch.paso.address.client.forms.fields.AbstractTextField;
import ch.paso.address.shared.entities.AbstractCodeType;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;

public class CodeEditForm extends AbstractForm {

	private ValueField m_valueField;
	private ActiveField m_activeField;
	private AbstractCodeType m_formData;
	private Long m_id;
	@Override
	protected List<Widget> getConfiguredFields() {
		ArrayList<Widget> result = new ArrayList<Widget>();
		result.add(new MainBox());
		return result;
	}

	@Override
	protected List<Button> getConfiguredButtons() {
		List<Button> result = new ArrayList<Button>();
		result.add(new OkButton());
		result.add(new CancelButton());
		return result;
	}

	public void setValueField(ValueField valueField) {
		m_valueField = valueField;
	}

	public ValueField getValueField() {
		return m_valueField;
	}

	public void setActiveField(ActiveField activeField) {
		m_activeField = activeField;
	}

	public ActiveField getActiveField() {
		return m_activeField;
	}

	public class MainBox extends AbstractGroupBox {

		@Override
		protected int getConfiguredNumberOfColumns() {
			return 1;
		}
		@Override
		protected List<Widget> getConfiguredFields() {
			ArrayList<Widget> result = new ArrayList<Widget>();
			setValueField(new ValueField());
			setActiveField(new ActiveField());
			result.add(getValueField());
			result.add(getActiveField());
			return result;
		}

		public class ValueField extends AbstractTextField {
			@Override
			protected String getConfiguredLabel() {
				return "Wert";
			}
		}

		public class ActiveField extends AbstractCheckboxfield {
			@Override
			protected String getConfiguredLabel() {
				return "Aktiv";
			}
		}
	}

	private void importData(AbstractCodeType data) {
		setFormData(data);
		getValueField().setValue(data.getText());
		getActiveField().setValue(data.isActive());
		setId(data.getId());
	}

	private AbstractCodeType exportData() {
		AbstractCodeType data = getFormData();
		if(data == null){
			return null;
		}
		data.setActive(getActiveField().getValue());
		data.setId(getId());
		data.setText(getValueField().getValue());
		return data;
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
	
	public class NewHandler implements IHandler{

		@Override
		public void execStore() {
			exportData();
		}

		@Override
		public void execLoad() {
			//TODO
		}
		
	}
	
	@Override
	protected String getConfiguredTitle() {
		return "Code editieren";
	}
	public void startNew(){
		startHandler(new NewHandler());
	}

	public void setFormData(AbstractCodeType formData) {
		m_formData = formData;
	}

	public AbstractCodeType getFormData() {
		return m_formData;
	}

	public void setId(Long id) {
		m_id = id;
	}

	public Long getId() {
		return m_id;
	}
}
