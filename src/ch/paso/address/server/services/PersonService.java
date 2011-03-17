package ch.paso.address.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	public PersonEntity storePerson(PersonEntity p){
		EntityManager em = EMFService.get().createEntityManager();
		em.persist(p);
		p = em.merge(p);
		return p;
	}

	public List<PersonEntity> getAllPersons(){
		EntityManager em = EMFService.get().createEntityManager();
		Query personQuery = em.createQuery("SELECT p FROM PersonEntity p");
		List resultList = personQuery.getResultList();
		List<PersonEntity> result = new ArrayList<PersonEntity>();
		result.addAll(resultList);
		return result;
	}
}
