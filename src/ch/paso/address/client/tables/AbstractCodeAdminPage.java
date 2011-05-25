package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.errorhandling.ErrorHandler;
import ch.paso.address.client.forms.CodeEditForm;
import ch.paso.address.client.services.ICodeService;
import ch.paso.address.client.services.ICodeServiceAsync;
import ch.paso.address.client.tables.columns.AbstractColumn;
import ch.paso.address.client.tables.columns.AbstractStringColumn;
import ch.paso.address.shared.entities.ICodeType;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * List all functions. Allow editing
 * 
 * @author flwidmer
 * 
 */
public abstract class AbstractCodeAdminPage extends Composite {

	private CodeTable m_theTable;
	private VerticalPanel m_panel;
	private ICodeType m_prototype;

	public AbstractCodeAdminPage() {
		setPanel(new VerticalPanel());
		initWidget(getPanel());
	}

	@Override
	protected void onLoad() {
		setPrototype(getConfiguredPrototype());
		setTheTable(new CodeTable());
		getPanel().add(getTheTable());
		Button newButton = new Button();
		newButton.setText("Neu");
		newButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				CodeEditForm form = new CodeEditForm();
				form.addCloseHandler(new CloseHandler<PopupPanel>() {
					@Override
					public void onClose(CloseEvent<PopupPanel> event) {
						reload();
					}
				});
				// TODO
				form.setFormData(getPrototype());
				form.startNew();
			}
		});
		getPanel().add(newButton);
		reload();
		// TODO delete button (admin only)
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

	public void setPanel(VerticalPanel panel) {
		m_panel = panel;
	}

	public VerticalPanel getPanel() {
		return m_panel;
	}

	public void setTheTable(CodeTable theTable) {
		m_theTable = theTable;
	}

	public CodeTable getTheTable() {
		return m_theTable;
	}

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

		public class EditButtonColumn extends AbstractColumn<ICodeType, String> {

			public EditButtonColumn() {
				super(new ButtonCell());
				setFieldUpdater(new FieldUpdater<ICodeType, String>() {

					@Override
					public void update(int index, ICodeType object, String value) {
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
				});
			}

			@Override
			public String getValue(ICodeType object) {
				return "Bearbeiten";
			}

		}

	}
}
