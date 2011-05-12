package ch.paso.address.shared.permission;

public interface IPermission {

	public static int PERMISSION_NONE = 0;
	public static int PERMISSION_ALL = 100;
	/**
	 * return the permissions name. This will be used for hashing
	 * @return
	 */
	String getName();

	/**
	 * return the access level
	 * <ul>
	 * <li>0: none</li>
	 * <li>100: all</li>
	 * </ul
	 * 
	 * @return
	 */
	int getAccessLevel();
}
