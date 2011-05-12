package ch.paso.address.shared.permission;

import java.util.HashMap;


public class PermissionStore {
	
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
	public boolean checkPermission(String permission){
		return checkPermission(permission, IPermission.PERMISSION_ALL);
	}

	public boolean checkPermission(String permission, int level){
		//TODO
		return false;
	}
	
}
