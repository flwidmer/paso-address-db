package ch.paso.address.client.forms.fields;

import java.util.ArrayList;
import java.util.List;

import ch.paso.address.client.errorhandling.Errorhandler;
import ch.paso.address.client.services.ICodeService;
import ch.paso.address.client.services.ICodeServiceAsync;
import ch.paso.address.shared.entities.ICodeType;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;

public abstract class AbstractSmartField<T extends ICodeType> extends
		AbstractLabeledFormField {

	private ListBox m_listBox;
	private T m_prototype;
	private List<T> m_codeList;
	private boolean m_initialized=false;
	private String m_initialValue;

	@Override
	protected void onLoad() {
		super.onLoad();
		init();
		execInit();
		add(getListBox());
	}

	protected void execInit() {

	}

	private void init() {
		setListBox(new ListBox(getConfiguredMultiSelect()));
		setPrototype(getConfiguredPrototype());
		loadCodes();
		setInitialized(true);
	}

	protected abstract T getConfiguredPrototype();

	private void loadCodes() {
		ICodeServiceAsync svc = GWT.create(ICodeService.class);
		svc.loadActiveCodes(getPrototype(),
				new AsyncCallback<List<ICodeType>>() {

					@SuppressWarnings("unchecked")
					@Override
					public void onSuccess(List<ICodeType> result) {
						getListBox().addItem("");
						for (ICodeType c : result) {
							getListBox().addItem(c.getText());
						}
						ArrayList<T> l = new ArrayList<T>();
						l.add(getPrototype());
						for (ICodeType c : result) {
							l.add((T) c);
						}
						setCodeList(l);
						if(m_initialValue != null){
							setValueString(m_initialValue);
						}
					}

					@Override
					public void onFailure(Throwable caught) {
						Errorhandler.handleError("Error during loading codes", caught);
					}
				});
	}

	protected boolean getConfiguredMultiSelect() {
		return false;
	}

	public void setListBox(ListBox listBox) {
		m_listBox = listBox;
	}

	public ListBox getListBox() {
		return m_listBox;
	}

	public void setPrototype(T prototype) {
		m_prototype = prototype;
	}

	public T getPrototype() {
		return m_prototype;
	}

	public T getValue() {
		int selectedIndex = getListBox().getSelectedIndex();
		return getCodeList().get(selectedIndex);
	}

	private void setCodeList(List<T> codeList) {
		m_codeList = codeList;
	}

	public List<T> getCodeList() {
		return m_codeList;
	}

	public void reloadCodes() {
		getListBox().clear();
		loadCodes();
	}

	public void setValue(Long value) {
		if(!isInitialized()){
			init();
		}
		if(getCodeList() == null){
			return;
		}
		int i = 0;
		for (ICodeType c : getCodeList()) {
			if (c.getId().equals(value)) {
				getListBox().setSelectedIndex(i);
				return;
			}
			i++;
		}
	}

	public String getValueString() {
		return getListBox().getValue(getListBox().getSelectedIndex());
	}

	public void setValueString(String value) {
		if(!isInitialized()){
			init();
		}
		if(getCodeList() == null){
			m_initialValue = value;
			return;
		}
		int i = 0;
		for (ICodeType c : getCodeList()) {
			if (c.getText() != null && c.getText().equals(value)) {
				getListBox().setSelectedIndex(i);
				return;
			}
			i++;
		}
	}

	public void setInitialized(boolean initialized) {
		m_initialized = initialized;
	}

	public boolean isInitialized() {
		return m_initialized;
	}
}
