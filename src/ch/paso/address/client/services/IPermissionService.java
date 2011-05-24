package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.UserEntity;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("/auth/permission")
public interface IPermissionService extends RemoteService {

	List<String> loadPermissions();

	UserEntity loadUserEntity(Long id);

	void storeUser(UserEntity u);

	void deleteUser(Long id);

}
