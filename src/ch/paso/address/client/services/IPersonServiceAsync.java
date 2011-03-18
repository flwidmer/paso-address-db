package ch.paso.address.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ch.paso.address.shared.entities.PersonEntity;

public interface IPersonServiceAsync {
	public void getPerson(Long key, AsyncCallback<PersonEntity> callback);

	public void storePerson(PersonEntity p, AsyncCallback<PersonEntity> callback);

	public void getAllPersons(AsyncCallback<List<PersonEntity>> callback);

	public void deletePerson(Long key, AsyncCallback<Void> callback);

}
