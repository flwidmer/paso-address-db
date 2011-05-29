package ch.paso.address.shared.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import org.apache.commons.io.filefilter.FalseFileFilter;

import ch.paso.address.shared.permission.Permission;

@Entity
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long m_id;
	@Basic
	// TODO unique constraint
	private String m_userName;
	@Basic(optional = false)
	private String m_password;
	@Basic
	private List<Permission> m_permissions;

	public void setUserName(String userName) {
		m_userName = userName;
	}

	public String getUserName() {
		return m_userName;
	}

	public void setPassword(String password) {
		m_password = password;
	}

	public String getPassword() {
		return m_password;
	}

	public void setId(Long id) {
		m_id = id;
	}

	public Long getId() {
		return m_id;
	}

	public void setPermissions(List<Permission> permissions) {
		m_permissions = permissions;
	}

	public List<Permission> getPermissions() {
		return m_permissions;
	}

}
