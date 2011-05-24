package ch.paso.address.client.tables;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.tables.columns.AbstractColumn;

import com.google.gwt.user.cellview.client.CellTable;

public abstract class AbstractTable<T> extends CellTable<T> {

	private List<T> m_rowsInternal = new ArrayList<T>();

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void onLoad() {
		List<AbstractColumn> col = getConfiguredColumns();
		for (AbstractColumn column : col) {
			addColumn(column, column.getConfiguredTitle());
			if (column.getConfiguredSortable()) {

			}
		}
	}

	@SuppressWarnings("rawtypes")
	protected abstract List<AbstractColumn> getConfiguredColumns();

	public boolean contains(T o){
		return getRowsInternal().contains(o);
	}
	/**
	 * Add a row at the end of the table.
	 * 
	 * @param row
	 */
	public void AddRow(T row) {
		getRowsInternal().add(row);
		setRowData(getRowsInternal());
	}

	/**
	 * display a list of rows. This will delete all previously displayeed rows.
	 * 
	 * @param rows
	 */
	public void setRows(List<T> rows) {
		setRowsInternal(rows);
		setRowData(getRowsInternal());
	}

	public void setRowsInternal(List<T> rowsInternal) {
		m_rowsInternal = rowsInternal;
	}

	public List<T> getRowsInternal() {
		return m_rowsInternal;
	}
	
	public void removeRow(int i){
		m_rowsInternal.remove(i);
		setRowData(m_rowsInternal);
	}
}
