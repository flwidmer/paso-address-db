package ch.paso.address.client.tables;

import ch.paso.address.shared.entities.ICodeType;
import ch.paso.address.shared.entities.FunctionCodeType;

public class FunctionAdminPage extends AbstractCodeAdminPage{

	@Override
	protected ICodeType getConfiguredPrototype() {
		return new FunctionCodeType();
	}

}
