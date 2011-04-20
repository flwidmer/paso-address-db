package ch.paso.address.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import ch.paso.address.client.services.ICodeService;
import ch.paso.address.server.storage.EMF;
import ch.paso.address.shared.entities.ICodeType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CodeService extends RemoteServiceServlet implements ICodeService {
	private static final long serialVersionUID = -513970397040657031L;

	@SuppressWarnings("unchecked")
	public <T extends ICodeType> List<T> loadCodes(T type) {
		EntityManager em = EMF.get().createEntityManager();
		@SuppressWarnings("rawtypes")
		List resultList = em.createQuery(
				"SELECT c FROM " + type.getClass().getName() + " c")
				.getResultList();
		List<T> result = new ArrayList<T>();
		result.addAll(resultList);
		em.close();
		return result;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ICodeType> loadActiveCodes(ICodeType type) {
		EntityManager em = EMF.get().createEntityManager();
		Query query = em.createQuery("SELECT c FROM " + type.getClass().getName() + " c WHERE c.m_active=:active");
		query.setParameter("active", true);
		List resultList = query.getResultList();
		List<ICodeType> result = new ArrayList<ICodeType>();
		result.addAll(resultList);
		em.close();
		return result;
	}

	public ICodeType storeCode(ICodeType data) {
		EntityManager em = EMF.get().createEntityManager();
		em.persist(data);
		em.merge(data);
		em.close();
		return data;
	}

	public ICodeType loadCode(ICodeType prototype, Long id) {
		if (prototype == null) {
			return null;
		}
		EntityManager em = EMF.get().createEntityManager();
		ICodeType result;
		try {
			if ((result = em.find(prototype.getClass(), id)) != null) {
				return result;
			}
			return null;
		} finally {
			em.close();
		}
	}
}
