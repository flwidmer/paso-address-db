package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.PersonEntity;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("PersonService")
public interface IPersonService extends RemoteService {
	/**
	 * Utility class for simplifying access to the instance of async service.
	 */
	public static class Util {
		private static IPersonServiceAsync instance;
		public static IPersonServiceAsync getInstance(){
			if (instance == null) {
				instance = GWT.create(IPersonService.class);
			}
			return instance;
		}
	}
	public PersonEntity getPerson(Long key);
	public PersonEntity storePerson(PersonEntity p);
	public List<PersonEntity> getAllPersons();
	public void deletePerson(Long key);
}
