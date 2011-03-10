package ch.paso.address.client.forms.fields;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;


public abstract class AbstractButton extends Button {
	
	private String m_label;
	private boolean m_initialized;
	@Override
	protected void onLoad() {
		super.onLoad();
		setWidth(getConfiguredWidth());
		setText(getConfiguredLabel());
		addClickHandler(getConfiguredClickHandler());
	}
	
	

	protected ClickHandler getConfiguredClickHandler() {
		return new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				execClick(event);
			}
		};
	}



	protected void execClick(ClickEvent event) {
	}



	protected String getConfiguredLabel(){
		return "";
	}
	
	protected String getConfiguredWidth(){
		return "50px";
	}
	public void setLabel(String label) {
		m_label = label;
	}

	public String getLabel() {
		return m_label;
	}

	public void setInitialized(boolean initialized) {
		m_initialized = initialized;
	}

	public boolean isInitialized() {
		return m_initialized;
	}

}
