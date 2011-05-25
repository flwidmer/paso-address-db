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
		EntityManager em = EMF.get().createEntityManager();
		Query query = em
				.createQuery("SELECT p FROM UserEntity p WHERE p.username = :username");
		query.setParameter("username", username);
		UserEntity result = (UserEntity) query.getSingleResult();
		em.close();
		if (result != null) {
			return result.getPermissions();
		}
		return null;
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
