package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.UserEntity;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IPermissionServiceAsync {

	void loadPermissions(AsyncCallback<List<String>> callback);

	void loadUserEntity(Long id, AsyncCallback<UserEntity> callback);

	void storeUser(UserEntity u, AsyncCallback<Void> callback);

	void deleteUser(Long id, AsyncCallback<Void> callback);

}
