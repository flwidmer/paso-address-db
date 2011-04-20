package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.ICodeType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CodeService")
public interface ICodeService extends RemoteService {

	public <T extends ICodeType>List<T> loadCodes(T type);

	public ICodeType storeCode(ICodeType data);

	public ICodeType loadCode(ICodeType prototype, Long id);

	List<ICodeType> loadActiveCodes(ICodeType type);

}
