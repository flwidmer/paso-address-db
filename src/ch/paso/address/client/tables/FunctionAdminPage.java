package ch.paso.address.client.tables;

import ch.paso.address.shared.entities.AbstractCodeType;
import ch.paso.address.shared.entities.FunctionCodeType;

public class FunctionAdminPage extends AbstractCodeAdminPage{

	@Override
	protected AbstractCodeType getConfiguredPrototype() {
		return new FunctionCodeType();
	}

}
