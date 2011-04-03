package ch.paso.address.shared.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Entity
@MappedSuperclass
public abstract class AbstractCodeType implements Serializable {
	private static final long serialVersionUID = -7251687021370850137L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long m_id;

	private String m_text;

	private boolean m_active;

	public void setId(Long id) {
		m_id = id;
	}

	public Long getId() {
		return m_id;
	}

	public String getText() {
		return m_text;
	}

	public void setActive(boolean active) {
		m_active = active;
	}

	public boolean isActive() {
		return m_active;
	}

	public String toString() {
		return getText();
	}

	public void setText(String text) {
		m_text = text;
	}
	
	public abstract String getCodeTypeName();
}
