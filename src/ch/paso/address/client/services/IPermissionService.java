package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.UserEntity;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("auth/permission")
public interface IPermissionService extends RemoteService {

	List<Permission> loadPermissions();

	UserEntity loadUserEntity(Long id);

	void storeUser(UserEntity u);

	void deleteUser(Long id);

	List<UserEntity> getAllUsers();

}
