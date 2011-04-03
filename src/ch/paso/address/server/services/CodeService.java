package ch.paso.address.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ch.paso.address.client.services.ICodeService;
import ch.paso.address.server.storage.EMF;
import ch.paso.address.shared.entities.AbstractCodeType;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CodeService extends RemoteServiceServlet implements ICodeService {
	private static final long serialVersionUID = -513970397040657031L;

	@SuppressWarnings("unchecked")
	public List<AbstractCodeType> loadCodes(AbstractCodeType type) {
		EntityManager em = EMF.get().createEntityManager();
		@SuppressWarnings("rawtypes")
		List resultList = em.createQuery(
				"SELECT c FROM " + type.getClass().getName() + " c")
				.getResultList();
		List<AbstractCodeType> result = new ArrayList<AbstractCodeType>();
		result.addAll(resultList);
		em.close();
		return result;
	}

	public AbstractCodeType storeCode(AbstractCodeType data) {
		EntityManager em = EMF.get().createEntityManager();
		em.persist(data);
		em.merge(data);
		em.close();
		return data;
	}

	public AbstractCodeType loadCode(Long id) {
		// TODO
		return null;
	}
}
