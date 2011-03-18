package ch.paso.address.server.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ch.paso.address.client.services.IPersonService;
import ch.paso.address.server.storage.EMF;
import ch.paso.address.shared.entities.PersonEntity;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@RemoteServiceRelativePath("person")
public class PersonService extends RemoteServiceServlet implements
		IPersonService {

	private static final long serialVersionUID = 1L;

	public PersonEntity getPerson(Long key) {
		EntityManager em = EMF.get().createEntityManager();
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
		EntityManager em = EMF.get().createEntityManager();
		em.persist(p);
		em.merge(p);
		em.close();
		return p;
	}

	public List<PersonEntity> getAllPersons(){
		EntityManager em = EMF.get().createEntityManager();
		List resultList = em.createQuery("SELECT p FROM PersonEntity p").getResultList();
		List<PersonEntity> result = new ArrayList<PersonEntity>();
		
		result.addAll(resultList);
		em.close();
		return result;
	}
	
	public void deletePerson(Long key){
		EntityManager em = EMF.get().createEntityManager();
		em.getTransaction().begin();
		PersonEntity p = em.find(PersonEntity.class, key);
		if(p != null){
			em.remove(p);
		}
		em.getTransaction().commit();
		em.close();
	}
}
