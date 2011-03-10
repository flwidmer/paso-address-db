package ch.paso.address.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ch.paso.address.shared.entities.PersonEntity;

public interface IPersonServiceAsync {
	public void getPerson(Long key, AsyncCallback<PersonEntity> callback);
}
