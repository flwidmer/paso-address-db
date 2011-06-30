package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.forms.CodeEditForm;
import ch.paso.address.client.services.ICodeService;
import ch.paso.address.client.services.ICodeServiceAsync;
import ch.paso.address.client.tables.columns.AbstractButtonColumn;
import ch.paso.address.client.tables.columns.AbstractColumn;
import ch.paso.address.client.tables.columns.AbstractStringColumn;
import ch.paso.address.shared.entities.ICodeType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * List all functions. Allow editing
 * 
 * @author flwidmer
 * 
 */
public abstract class AbstractCodeAdminPage extends
		AbstractTablePage<ICodeType> {

	private ICodeType m_prototype;

	@Override
	protected boolean getConfiguredNewButtonVisible() {
		return true;
	}

	@Override
	protected void execInit() {
		setPrototype(getConfiguredPrototype());
	}

	@Override
	protected void execNewClick() {
		CodeEditForm form = new CodeEditForm();
		form.addCloseHandler(new CloseHandler<PopupPanel>() {
			@Override
			public void onClose(CloseEvent<PopupPanel> event) {
				reload();
			}
		});
		form.setFormData(getPrototype());
		form.startNew();
	}

	@SuppressWarnings("rawtypes")
	public void reload() {
		ICodeServiceAsync svc = GWT.create(ICodeService.class);
		svc.loadCodes(getConfiguredPrototype(), new AsyncCallback<List>() {

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(List result) {
				getTheTable().setRowData(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				ErrorHandler.handleError("Error during loading", caught);
			}
		});

	}

	protected abstract ICodeType getConfiguredPrototype();

	public void setPrototype(ICodeType prototype) {
		m_prototype = prototype;
	}

	public ICodeType getPrototype() {
		return m_prototype;
	}

	public class CodeTable extends AbstractTable<ICodeType> {

		@Override
		protected List<AbstractColumn<ICodeType, ?>> getConfiguredColumns() {
			ArrayList<AbstractColumn<ICodeType, ?>> result = new ArrayList<AbstractColumn<ICodeType, ?>>();
			result.add(new ValueColumn());
			result.add(new ActiveColumn());
			result.add(new EditButtonColumn());
			result.add(new DeleteButtonColumn());
			return result;
		}

		public class ValueColumn extends AbstractStringColumn<ICodeType> {

			@Override
			public String getValue(ICodeType object) {
				return object.getText();
			}

			@Override
			public String getConfiguredTitle() {
				return getPrototype().getCodeTypeName();
			}

		}

		public class ActiveColumn extends AbstractStringColumn<ICodeType> {

			@Override
			public String getValue(ICodeType object) {
				if (object.isActive()) {
					return "X";
				} else {
					return "";
				}
			}

			@Override
			public String getConfiguredTitle() {
				return "Aktiv";
			}

		}

		public class EditButtonColumn extends AbstractButtonColumn<ICodeType> {

			@Override
			public String getValue(ICodeType object) {
				return "Bearbeiten";
			}

			@Override
			protected void execOnClick(int index, ICodeType object, String value) {
				CodeEditForm form = new CodeEditForm();
				form.addCloseHandler(new CloseHandler<PopupPanel>() {
					public void onClose(CloseEvent<PopupPanel> event) {
						reload();
					}
				});
				form.setId(object.getId());
				form.setFormData(getPrototype());
				form.startModify();

			}

		}

		public class DeleteButtonColumn extends AbstractButtonColumn<ICodeType> {

			@Override
			protected String getConfiguredLabel() {
				return "Löschen";
			}

			@Override
			protected void execOnClick(int index, ICodeType object, String value) {
				// TODO
			}

		}

	}

	@Override
	protected CellTable<ICodeType> getConfiguredTable() {
		return new CodeTable();
	}
}
