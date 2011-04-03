package ch.paso.address.client.tables;

import ch.paso.address.shared.entities.AbstractCodeType;
import ch.paso.address.shared.entities.StufeCodeType;

public class StufeAdminPage extends AbstractCodeAdminPage {

	@Override
	protected AbstractCodeType getConfiguredPrototype() {
		return new StufeCodeType();
	}

}
