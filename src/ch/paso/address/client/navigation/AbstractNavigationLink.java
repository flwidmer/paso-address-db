package ch.paso.address.client.navigation;


import ch.paso.address.client.Ch_paso_address;
import ch.paso.address.shared.permission.Permission;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractNavigationLink extends Anchor{
	
	private Navigation m_navigation;
	
	public AbstractNavigationLink(Navigation n) {
		m_navigation = n;
	}
	
	@Override
	protected void onLoad() {
		setStyleName("NavLink");
		setText(getConfiguredText());
		addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				m_navigation.switchTo(getConfiguredTarget());
			}
		});
		setVisible(Ch_paso_address.checkPermission(getConfiguredPermission()));
	}
	
	protected abstract String getConfiguredText();
	
	protected abstract Widget getConfiguredTarget();
	
	protected Permission getConfiguredPermission(){
		return null;
	}
	
	
}
