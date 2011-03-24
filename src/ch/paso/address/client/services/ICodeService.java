package ch.paso.address.client.services;

import java.util.List;

import ch.paso.address.shared.entities.AbstractCodeType;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("CodeService")
public interface ICodeService extends RemoteService {

	public List<AbstractCodeType> loadCodes(AbstractCodeType type);

	public AbstractCodeType storeCode(AbstractCodeType data);

	public AbstractCodeType loadCode(Long id);

}
