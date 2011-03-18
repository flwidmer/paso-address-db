package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.tables.columns.AbstractColumn;

import com.google.gwt.user.cellview.client.CellTable;

public abstract class AbstractTable<T> extends CellTable<T>{
	
	@Override
	protected void onLoad() {
		List<AbstractColumn> col = getConfiguredColumns();
		for (AbstractColumn column : col) {
			addColumn(column,column.getConfiguredTitle());
			if(column.getConfiguredSortable()){
				
			}
		}
	}

	protected abstract  List<AbstractColumn> getConfiguredColumns();
}
