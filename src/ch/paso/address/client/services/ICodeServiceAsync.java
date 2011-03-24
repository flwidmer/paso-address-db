
package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.AbstractCodeType;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ICodeServiceAsync {

	public void loadCodes(AbstractCodeType type,
			AsyncCallback<List<AbstractCodeType>> callback);

	public void storeCode(AbstractCodeType data, AsyncCallback<AbstractCodeType> callback);

	public void loadCode(Long id, AsyncCallback<AbstractCodeType> callback);

}
