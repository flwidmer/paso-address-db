package ch.paso.address.shared.permission;

import java.util.HashMap;

public class PermissionStore {
	//TODO use

	private HashMap<String, Integer> m_permissionCache;

	/**
	 * load the permissions for the current user
	 */
	public void loadPermissions() {
		// TODO
	}

	/**
	 * check if the current user has the ALL permission of he given name
	 * convenience function
	 * 
	 * @param permission
	 * @return
	 */
	public boolean checkPermissionAll(String permission) {
		return checkPermission(permission, IPermission.PERMISSION_ALL);
	}

	/**
	 * check if the user has the permission with the given level
	 * @param permission
	 * @param level
	 * @return
	 */
	public boolean checkPermission(String permission, int level) {
		Integer storedLevel = m_permissionCache.get(permission);
		if (storedLevel == null || storedLevel < level || storedLevel == 0) {
			return false;
		} else {
			return true;
		}
	}

}
