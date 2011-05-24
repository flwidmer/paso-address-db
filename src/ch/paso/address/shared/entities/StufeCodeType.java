package ch.paso.address.shared.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StufeCodeType implements ICodeType {

	@SuppressWarnings("unused")
	private static final long serialVersionUID1 = 1337327221346875208L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.paso.address.shared.entities.ICode#getCodeTypeName()
	 */
	@Override
	public String getCodeTypeName() {
		return "Stufe";
	}

	private static final long serialVersionUID = -7251687021370850137L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long m_id;

	private String m_text;

	private boolean m_active;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.paso.address.shared.entities.ICode#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		m_id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.paso.address.shared.entities.ICode#getId()
	 */
	@Override
	public Long getId() {
		return m_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.paso.address.shared.entities.ICode#getText()
	 */
	@Override
	public String getText() {
		return m_text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.paso.address.shared.entities.ICode#setActive(boolean)
	 */
	@Override
	public void setActive(boolean active) {
		m_active = active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.paso.address.shared.entities.ICode#isActive()
	 */
	@Override
	public boolean isActive() {
		return m_active;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.paso.address.shared.entities.ICode#toString()
	 */
	@Override
	public String toString() {
		return getText();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.paso.address.shared.entities.ICode#setText(java.lang.String)
	 */
	@Override
	public void setText(String text) {
		m_text = text;
	}
}
