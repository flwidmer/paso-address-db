package ch.paso.address.client.tables.columns;

import ch.paso.address.client.Ch_paso_address;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.Column;

public abstract class AbstractColumn<T, C> extends Column<T, C> {

	public AbstractColumn(Cell<C> cell) {
		super(cell);

	}

	public String getConfiguredTitle() {
		return "";
	}

	public boolean getConfiguredSortable() {
		return false;
	}

	protected Permission getConfiguredPermission() {
		return null;
	}

	public boolean grantedVisible() {
		Permission reqPerm = getConfiguredPermission();
		if (reqPerm == null) {
			return true;
		}
		return Ch_paso_address.checkPermission(reqPerm);
	}
}
