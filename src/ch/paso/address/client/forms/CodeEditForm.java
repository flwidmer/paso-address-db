package ch.paso.address.client.forms;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.forms.CodeEditForm.MainBox.ActiveField;
import ch.paso.address.client.forms.CodeEditForm.MainBox.ValueField;
import ch.paso.address.client.forms.fields.AbstractCheckboxfield;
import ch.paso.address.client.forms.fields.AbstractGroupBox;
import ch.paso.address.client.forms.fields.AbstractTextField;
import ch.paso.address.client.services.ICodeService;
import ch.paso.address.client.services.ICodeServiceAsync;
import ch.paso.address.shared.entities.ICodeType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class CodeEditForm extends AbstractForm {

	private ValueField m_valueField;
	private ActiveField m_activeField;
	private ICodeType m_formData;
	private Long m_id;

	@Override
	protected List<Widget> getConfiguredFields() {
		ArrayList<Widget> result = new ArrayList<Widget>();
		result.add(new MainBox());
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

			@Override
			protected boolean getConfiguredInitValue() {
				return true;
			}
		}
	}

	private void importData(ICodeType data) {
		setFormData(data);
		getValueField().setValue(data.getText());
		getActiveField().setValue(data.isActive());
		setId(data.getId());
	}

	private ICodeType exportData() {
		ICodeType data = getFormData();
		if (data == null) {
			return null;
		}
		data.setActive(getActiveField().getValue());
		data.setId(getId());
		data.setText(getValueField().getValue());
		return data;
	}


	

	public class ModifyHandler implements IHandler {

		@Override
		public void execStore() {
			ICodeType exportData = exportData();
			ICodeServiceAsync svc = GWT.create(ICodeService.class);
			svc.storeCode(exportData, new AsyncCallback<ICodeType>() {

				@Override
				public void onSuccess(ICodeType result) {
					hide();
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorHandler.handleError("Error during storing", caught);
				}
			});
		}

		@Override
		public void execLoad() {
			ICodeServiceAsync svc = GWT.create(ICodeService.class);
			svc.loadCode(getFormData(),getId(), new AsyncCallback<ICodeType>() {

				@Override
				public void onSuccess(ICodeType result) {
					importData(result);
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorHandler.handleError("Error during loading", caught);				}
			});
		}

	}

	public class NewHandler implements IHandler {

		@Override
		public void execStore() {
			ICodeType exportData = exportData();
			ICodeServiceAsync svc = GWT.create(ICodeService.class);
			svc.storeCode(exportData, new AsyncCallback<ICodeType>() {

				@Override
				public void onSuccess(ICodeType result) {
					hide();
				}

				@Override
				public void onFailure(Throwable caught) {
					ErrorHandler.handleError("Error during storing", caught);
				}
			});
		}

		@Override
		public void execLoad() {
			//NOP
		}

	}

	@Override
	protected String getConfiguredTitle() {
		return "Code editieren";
	}

	public void startNew() {
		startHandler(new NewHandler());
	}

	public void startModify() {
		startHandler(new ModifyHandler());
	}

	public void setFormData(ICodeType formData) {
		m_formData = formData;
	}

	public ICodeType getFormData() {
		return m_formData;
	}

	public void setId(Long id) {
		m_id = id;
	}

	public Long getId() {
		return m_id;
	}
}
