package ch.paso.address.shared.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

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
	@Transient
	private List<Permission> m_permissions;
	@Basic
	private String[] m_permissionStorage;

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

	@PrePersist
	public void prePersist() {
		m_permissionStorage = new String[m_permissions.size()];
		for (int i = 0; i < m_permissions.size(); i++) {
			m_permissionStorage[i] = m_permissions.get(i).getName() + ";"
					+ m_permissions.get(i).getLevel();
		}
	}

	@PreUpdate
	public void preUpdate() {
		prePersist();
	}

	@PostLoad
	public void postLoad() {
		m_permissions = new ArrayList<Permission>(m_permissionStorage.length);
		for (String s : m_permissionStorage) {
			String[] split = s.split(";");
			Permission p = new Permission();
			p.setName(split[0]);
			p.setLevel(Integer.parseInt(split[1]));
			m_permissions.add(p);
		}
	}
}
