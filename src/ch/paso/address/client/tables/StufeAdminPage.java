package ch.paso.address.client.tables;

import ch.paso.address.shared.entities.ICodeType;
import ch.paso.address.shared.entities.StufeCodeType;

public class StufeAdminPage extends AbstractCodeAdminPage {

	@Override
	protected ICodeType getConfiguredPrototype() {
		return new StufeCodeType();
	}

}
