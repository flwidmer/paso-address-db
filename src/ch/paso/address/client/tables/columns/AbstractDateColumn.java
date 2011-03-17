package ch.paso.address.client.tables.columns;

import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.i18n.client.DateTimeFormat;

public abstract class AbstractDateColumn<T> extends AbstractColumn<T, Date>{
	public AbstractDateColumn() {
		super(new DateCell(DateTimeFormat.getFormat("dd.MM.yyyy")));
	}
	

}
