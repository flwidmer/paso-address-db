package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.forms.CodeEditForm;
import ch.paso.address.client.forms.PersonForm;
import ch.paso.address.client.services.ICodeService;
import ch.paso.address.client.services.ICodeServiceAsync;
import ch.paso.address.client.tables.columns.AbstractCheckBoxColumn;
import ch.paso.address.client.tables.columns.AbstractColumn;
import ch.paso.address.client.tables.columns.AbstractStringColumn;
import ch.paso.address.shared.entities.AbstractCodeType;
import ch.paso.address.shared.entities.FunctionCodeType;

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
	private AbstractCodeType m_prototype;

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
		// TODO new button
	}

	public void reload() {
		ICodeServiceAsync svc = GWT.create(ICodeService.class);
		svc.loadCodes(getConfiguredPrototype(), new AsyncCallback<List<AbstractCodeType>>() {
			
			@Override
			public void onSuccess(List<AbstractCodeType> result) {
				getTheTable().setRowData(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	protected abstract AbstractCodeType getConfiguredPrototype();

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

	public void setPrototype(AbstractCodeType prototype) {
		m_prototype = prototype;
	}

	public AbstractCodeType getPrototype() {
		return m_prototype;
	}

	public class CodeTable extends AbstractTable<AbstractCodeType> {

		@SuppressWarnings("rawtypes")
		@Override
		protected List<AbstractColumn> getConfiguredColumns() {
			ArrayList<AbstractColumn> result = new ArrayList<AbstractColumn>();
			result.add(new ValueColumn());
			result.add(new ActiveColumn());
			result.add(new EditButtonColumn());
			return result;
		}

		public class ValueColumn extends AbstractStringColumn<AbstractCodeType> {

			@Override
			public String getValue(AbstractCodeType object) {
				return object.getText();
			}

			@Override
			public String getConfiguredTitle() {
				return getPrototype().getCodeTypeName();
			}

		}

		public class ActiveColumn extends
				AbstractCheckBoxColumn<AbstractCodeType> {

			@Override
			public Boolean getValue(AbstractCodeType object) {
				return object.isActive();
			}

			@Override
			public String getConfiguredTitle() {
				return "Aktiv";
			}

		}

		public class EditButtonColumn extends
				AbstractColumn<AbstractCodeType, String> {

			public EditButtonColumn() {
				super(new ButtonCell());
				setFieldUpdater(new FieldUpdater<AbstractCodeType, String>() {

					@Override
					public void update(int index, AbstractCodeType object,
							String value) {
						CodeEditForm form = new CodeEditForm();
						form.addCloseHandler(new CloseHandler<PopupPanel>() {
							public void onClose(CloseEvent<PopupPanel> event) {
								reload();
							}
						});
						// TODO
						// form.setId(object.getId());
						// form.startModify();

					}
				});
			}

			@Override
			public String getValue(AbstractCodeType object) {
				return "Bearbeiten";
			}

		}

	}
}
