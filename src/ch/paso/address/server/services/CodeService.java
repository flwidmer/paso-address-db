package ch.paso.address.server.services;

import java.util.List;

import javax.persistence.EntityManager;

import ch.paso.address.client.services.ICodeService;
import ch.paso.address.server.storage.EMF;
import ch.paso.address.shared.entities.AbstractCodeType;

public class CodeService implements ICodeService {

	public List<AbstractCodeType> loadCodes(AbstractCodeType type) {
		EntityManager em = EMF.get().createEntityManager();
		//TODO
		return null;
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
