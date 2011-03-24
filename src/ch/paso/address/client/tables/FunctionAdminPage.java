package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.forms.CodeEditForm;
import ch.paso.address.client.forms.PersonForm;
import ch.paso.address.client.tables.columns.AbstractCheckBoxColumn;
import ch.paso.address.client.tables.columns.AbstractColumn;
import ch.paso.address.client.tables.columns.AbstractStringColumn;
import ch.paso.address.shared.entities.FunctionCodeType;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
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
public class FunctionAdminPage extends Composite {

	private FunctionTable m_theTable;
	private VerticalPanel m_panel;

	public FunctionAdminPage() {
		setPanel(new VerticalPanel());
		initWidget(getPanel());
	}

	@Override
	protected void onLoad() {
		setTheTable(new FunctionTable());
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
				form.setFormData(new FunctionCodeType());
				form.startNew();
			}
		});
		getPanel().add(newButton);
		reload();
		// TODO new button
	}

	public void reload() {
		// TODO Auto-generated method stub

	}

	public void setPanel(VerticalPanel panel) {
		m_panel = panel;
	}

	public VerticalPanel getPanel() {
		return m_panel;
	}

	public void setTheTable(FunctionTable theTable) {
		m_theTable = theTable;
	}

	public FunctionTable getTheTable() {
		return m_theTable;
	}

	public class FunctionTable extends AbstractTable<FunctionCodeType> {

		@SuppressWarnings("rawtypes")
		@Override
		protected List<AbstractColumn> getConfiguredColumns() {
			ArrayList<AbstractColumn> result = new ArrayList<AbstractColumn>();
			result.add(new ValueColumn());
			result.add(new ActiveColumn());
			result.add(new EditButtonColumn());
			return result;
		}

		public class ValueColumn extends AbstractStringColumn<FunctionCodeType> {

			@Override
			public String getValue(FunctionCodeType object) {
				return object.getText();
			}

			@Override
			public String getConfiguredTitle() {
				return "Funktion";
			}

		}

		public class ActiveColumn extends
				AbstractCheckBoxColumn<FunctionCodeType> {

			@Override
			public Boolean getValue(FunctionCodeType object) {
				return object.isActive();
			}

			@Override
			public String getConfiguredTitle() {
				return "Aktiv";
			}

		}

		public class EditButtonColumn extends
				AbstractColumn<FunctionCodeType, String> {

			public EditButtonColumn() {
				super(new ButtonCell());
				setFieldUpdater(new FieldUpdater<FunctionCodeType, String>() {

					@Override
					public void update(int index, FunctionCodeType object,
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
			public String getValue(FunctionCodeType object) {
				return "Bearbeiten";
			}

		}

	}
}
