package ch.paso.address.shared.entities;

import javax.persistence.Entity;

@Entity
public class FunctionCodeType extends AbstractCodeType {

	private static final long serialVersionUID = -5137695175959252705L;

	@Override
	public String getCodeTypeName() {
		return "Funktion";
	}

	
}
