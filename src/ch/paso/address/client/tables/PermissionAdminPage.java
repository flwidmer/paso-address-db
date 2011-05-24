package ch.paso.address.client.tables;

import ch.paso.address.shared.entities.ICodeType;
import ch.paso.address.shared.entities.PermissionCodeType;

public class PermissionAdminPage extends AbstractCodeAdminPage {

	@Override
	protected ICodeType getConfiguredPrototype() {
		return new PermissionCodeType();
	}

}
