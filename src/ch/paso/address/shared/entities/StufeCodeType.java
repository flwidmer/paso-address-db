package ch.paso.address.shared.entities;

import javax.persistence.Entity;

@Entity
public class StufeCodeType extends AbstractCodeType{

	private static final long serialVersionUID = 1337327221346875208L;

	@Override
	public String getCodeTypeName() {
		return "Stufe";
	}

}
