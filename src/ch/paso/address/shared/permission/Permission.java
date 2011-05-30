package ch.paso.address.shared.permission;

import java.io.Serializable;

public class Permission implements Comparable<Permission>, Serializable {
	private static final long serialVersionUID = -2218294889469266059L;
	private String m_name;
	private int m_level;

	public void setLevel(int level) {
		m_level = level;
	}

	public int getLevel() {
		return m_level;
	}

	public void setName(String name) {
		m_name = name;
	}

	public String getName() {
		return m_name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Permission) {
			return ((Permission) obj).getName().equals(m_name)
					&& ((Permission) obj).getLevel() == m_level;
		} else {
			return false;
		}
	}

	@Override
	public int compareTo(Permission o) {
		int r = m_name.compareTo(o.getName());
		if (r == 0) {
			if (m_level > o.getLevel()) {
				return 1;
			} else if (m_level < o.getLevel()) {
				return -1;
			} else {
				return 0;
			}
		} else {
			return r;
		}
	}
}