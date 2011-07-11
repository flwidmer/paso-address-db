
package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.ICodeType;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ICodeServiceAsync {
	

	public void storeCode(ICodeType data, AsyncCallback<ICodeType> callback);

	public void loadCode(ICodeType prototype, Long id, AsyncCallback<ICodeType> callback);

	public void loadActiveCodes(ICodeType type,
			AsyncCallback<List<ICodeType>> callback);

	public void loadCodes(ICodeType type, AsyncCallback<List> callback);

	void deleteCode(ICodeType prototype, Long id, AsyncCallback<Void> callback);

}
