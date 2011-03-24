package ch.paso.address.client.tables.columns;

import com.google.gwt.cell.client.CheckboxCell;

public abstract class AbstractCheckBoxColumn<T> extends AbstractColumn<T, Boolean>{

	public AbstractCheckBoxColumn() {
		super(new CheckboxCell());
	}

}
