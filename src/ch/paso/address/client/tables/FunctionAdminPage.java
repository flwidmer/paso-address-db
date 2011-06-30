package ch.paso.address.client.tables;

import com.google.gwt.user.cellview.client.CellTable;

import ch.paso.address.shared.entities.ICodeType;
import ch.paso.address.shared.entities.FunctionCodeType;

public class FunctionAdminPage extends AbstractCodeAdminPage{

	@Override
	protected ICodeType getConfiguredPrototype() {
		return new FunctionCodeType();
	}

}
