package ch.paso.address.server.services;

import java.util.List;

import ch.paso.address.client.services.IPermissionService;
import ch.paso.address.shared.entities.UserEntity;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PermissionService extends RemoteServiceServlet implements
		IPermissionService {

	private static final long serialVersionUID = 3481602017941236244L;

	public List<String> loadPermissions() {
		String username = (String) getThreadLocalRequest().getSession()
				.getAttribute("user");
		// TODO load permissions
		return null;
	}

	@Override
	public UserEntity loadUserEntity(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeUser(UserEntity u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub

	}

}
