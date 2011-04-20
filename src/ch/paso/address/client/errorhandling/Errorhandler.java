package ch.paso.address.client.errorhandling;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;


public class Errorhandler {

	public  static void handleError(Exception e){
	}
	
	public  static void handleError(String msg, Exception e){
		PopupPanel pp = new PopupPanel();
		pp.add(new HTML("sss"));
		pp.setGlassEnabled(true);
		pp.center();
		pp.show();
	}
}
