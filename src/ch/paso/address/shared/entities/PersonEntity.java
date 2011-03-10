package ch.paso.address.shared.entities;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	private Long m_id;
	public Long getId() {
		return m_id;
	}
	public void setId(Long id) {
		m_id = id;
	}
	public String getFirstName() {
		return m_firstName;
	}
	public void setFirstName(String firstName) {
		m_firstName = firstName;
	}
	public String getLastName() {
		return m_lastName;
	}
	public void setLastName(String lastName) {
		m_lastName = lastName;
	}
	public String getVulgo() {
		return m_vulgo;
	}
	public void setVulgo(String vulgo) {
		m_vulgo = vulgo;
	}
	public Date getBirthDate() {
		return m_birthDate;
	}
	public void setBirthDate(Date birthDate) {
		m_birthDate = birthDate;
	}
	public Date getEntry() {
		return m_entry;
	}
	public void setEntry(Date entry) {
		m_entry = entry;
	}
	public Date getLeft() {
		return m_left;
	}
	public void setLeft(Date left) {
		m_left = left;
	}
	public boolean isActive() {
		return m_active;
	}
	public void setActive(boolean active) {
		m_active = active;
	}
	private String m_firstName;
	private String m_lastName;
	private String m_vulgo;
	private Date m_birthDate;
	private Date m_entry;
	private Date m_left;
	private boolean m_active;
}
