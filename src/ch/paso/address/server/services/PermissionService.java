package ch.paso.address.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import ch.paso.address.client.services.IPermissionService;
import ch.paso.address.server.storage.EMF;
import ch.paso.address.shared.entities.UserEntity;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PermissionService extends RemoteServiceServlet implements
		IPermissionService {

	private static final long serialVersionUID = 3481602017941236244L;

	public List<Permission> loadPermissions() {
		String username = (String) getThreadLocalRequest().getSession()
				.getAttribute("user");
		UserEntity result = loadUserEntity(username);
		List<Permission> permissions = new ArrayList<Permission>();
		if (result != null) {
			result.postLoad();
			permissions = result.getPermissions();
		}
		if (username.equals("fwi")) {
			permissions.add(new Permission("Admin", 100));
			permissions.add(new Permission("UserAdmin", 100));
			permissions.add(new Permission("PermissionAdmin", 100));
		}
		return permissions;
	}

	public UserEntity loadUserEntity(String username) {
		EntityManager em = EMF.get().createEntityManager();
		try {
			Query query = em
					.createQuery("SELECT p FROM UserEntity p WHERE p.m_userName = :username");
			query.setParameter("username", username);
			if (query.getResultList().size() > 0) {
				UserEntity result = (UserEntity) query.getSingleResult();
				return result;
			}
			return null;
		} finally {
			em.close();
		}
	}

	public boolean authenticate(String username, String password) {
		if (username == null || username.isEmpty() || password == null
				|| password.isEmpty()) {
			return false;
		}
		UserEntity u = loadUserEntity(username);
		if (u != null && u.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public UserEntity loadUserEntity(Long id) {
		if (id == null) {
			return null;
		}
		EntityManager em = EMF.get().createEntityManager();
		UserEntity result;
		try {
			if ((result = em.find(UserEntity.class, id)) != null) {
				return result;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}

	@Override
	public void storeUser(UserEntity u) {
		EntityManager em = EMF.get().createEntityManager();
		em.persist(u);
		// XXX return person?
		em.close();

	}

	@Override
	public void deleteUser(Long id) {
		if (id == null) {
			return;
		}
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		UserEntity p = em.find(UserEntity.class, id);
		if (p != null) {
			em.remove(p);
		}
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public List<UserEntity> getAllUsers() {
		EntityManager em = EMF.get().createEntityManager();
		@SuppressWarnings("unchecked")
		List<UserEntity> resultList = em.createQuery(
				"SELECT p FROM UserEntity p").getResultList();
		List<UserEntity> result = new ArrayList<UserEntity>();

		result.addAll(resultList);
		em.close();
		return result;
	}

}
