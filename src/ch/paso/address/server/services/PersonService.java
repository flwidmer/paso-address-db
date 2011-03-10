package ch.paso.address.server.services;

import javax.persistence.EntityManager;

import ch.paso.address.client.services.IPersonService;
import ch.paso.address.server.storage.EMFService;
import ch.paso.address.shared.entities.PersonEntity;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@RemoteServiceRelativePath("person")
public class PersonService extends RemoteServiceServlet implements
		IPersonService {

	private static final long serialVersionUID = 1L;

	public PersonEntity getPerson(Long key) {
		EntityManager em = EMFService.get().createEntityManager();
		PersonEntity result;
		try {
			if ((result = em.find(PersonEntity.class, key)) != null) {
				return result;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}

}
