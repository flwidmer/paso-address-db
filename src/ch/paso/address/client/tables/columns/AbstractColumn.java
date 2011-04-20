package ch.paso.address.client.tables.columns;

import org.datanucleus.query.evaluator.memory.SetExpression;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.Column;

public abstract class AbstractColumn <T, C> extends Column<T, C> {

	public AbstractColumn(Cell<C> cell) {
		super(cell);
	}
	
	public String getConfiguredTitle(){
		return "";
	}
	
	public boolean getConfiguredSortable(){
		return false;
	}
	
	
}
