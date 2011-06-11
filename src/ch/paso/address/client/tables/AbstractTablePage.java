package ch.paso.address.client.tables;

import ch.paso.address.client.Ch_paso_address;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class AbstractTablePage<T> extends Composite {
	private CellTable<T> m_theTable;

	public AbstractTablePage() {
		setTheTable(getConfiguredTable());
		VerticalPanel hp = new VerticalPanel();
		hp.add(getTheTable());
		if (getConfiguredNewButtonVisible() && newButtonGrantedVisible()) {
			Button newButton = new Button();
			newButton.setText("Neu");
			newButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					execNewClick();
				}
			});
			hp.add(newButton);
		}
		initWidget(hp);
	}

	private boolean newButtonGrantedVisible() {
		if(getConfiguredNewButtonPermission()!=null){
			return Ch_paso_address.checkPermission(getConfiguredNewButtonPermission());
		}else{
			return true;
		}
	}
	
	protected Permission getConfiguredNewButtonPermission(){
		return null;
	}

	@Override
	protected void onLoad() {
		reload();
	}

	protected abstract CellTable<T> getConfiguredTable();

	protected abstract void reload();

	protected boolean getConfiguredNewButtonVisible() {
		return false;
	}

	protected void execNewClick() {

	}

	public void setTheTable(CellTable<T> theTable) {
		m_theTable = theTable;
	}

	public CellTable<T> getTheTable() {
		return m_theTable;
	}
}
