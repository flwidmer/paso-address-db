package ch.paso.address.shared.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PersonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public void setStreet(String street) {
		m_street = street;
	}

	public String getStreet() {
		return m_street;
	}

	public void setPlz(String plz) {
		m_plz = plz;
	}

	public String getPlz() {
		return m_plz;
	}

	public void setTown(String town) {
		m_town = town;
	}

	public String getTown() {
		return m_town;
	}

	public void setPhone(String phone) {
		m_phone = phone;
	}

	public String getPhone() {
		return m_phone;
	}

	public void setCell(String cell) {
		m_cell = cell;
	}

	public String getCell() {
		return m_cell;
	}

	public void setEmail(String email) {
		m_email = email;
	}

	public String getEmail() {
		return m_email;
	}

	public void setFunction(FunctionCodeType function) {
		m_function = function;
	}

	public FunctionCodeType getFunction() {
		return m_function;
	}

	@Basic
	private String m_firstName;
	@Basic
	private String m_lastName;
	@Basic
	private String m_vulgo;
	@Basic
	private Date m_birthDate;
	@Basic
	private Date m_entry;
	@Basic
	private Date m_left;
	@Basic
	private boolean m_active;
	@Basic
	private String m_street;
	@Basic
	private String m_plz;
	@Basic
	private String m_town;
	@Basic
	private String m_phone;
	@Basic
	private String m_cell;
	@Basic
	private String m_email;
	//@ManyToOne
	private FunctionCodeType m_function;
}
