package ch.paso.address.client.tables.columns;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;

public abstract class AbstractButtonColumn<T> extends
		AbstractColumn<T, String> {

	public AbstractButtonColumn() {
		super(new ButtonCell());
		setFieldUpdater(new FieldUpdater<T, String>() {

			@Override
			public void update(final int index, T object,
					String value) {

				execOnClick(index, object, value);
			}
		});
	}
	
	protected String getConfiguredLabel(){
		return "";
	}

	@Override
	public String getValue(T object) {
		return getConfiguredLabel();
	}

	protected abstract void execOnClick(int index,T object, String value);

}