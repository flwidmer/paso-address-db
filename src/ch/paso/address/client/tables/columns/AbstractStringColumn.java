package ch.paso.address.client.tables.columns;

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;

public abstract class AbstractStringColumn<T> extends AbstractColumn<T,String>{

	public AbstractStringColumn() {
		super(new TextCell());
	}
}
