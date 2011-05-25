package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.UserEntity;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IPermissionServiceAsync {

	void loadPermissions(AsyncCallback<List<Permission>> callback);

	void loadUserEntity(Long id, AsyncCallback<UserEntity> callback);

	void storeUser(UserEntity u, AsyncCallback<Void> callback);

	void deleteUser(Long id, AsyncCallback<Void> callback);

	void getAllUsers(AsyncCallback<List<UserEntity>> callback);

}
