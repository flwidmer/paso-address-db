package ch.paso.address.client.forms.fields;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.DateBox;

public abstract class AbstractDateField extends AbstractLabeledFormField {
	private DateBox m_datePicker;
	private DateTimeFormat m_Format;
	private boolean m_initialized = false;
	private String m_width;

	public void setDatePicker(DateBox datePicker) {
		m_datePicker = datePicker;
	}

	public DateBox getDatePicker() {
		return m_datePicker;
	}

	@Override
	protected void onLoad() {
		super.onLoad();
		setFormat(getConfiguredDateFormat());
		setDatePicker(new DateBox());
		add(getDatePicker());
		getDatePicker().setFormat(new DateBox.DefaultFormat(getFormat()));
		setWidth(getConfiguredWidth());
		getDatePicker().setWidth(getWidth());
		setInitialized(true);
		execInit();
	}

	protected void execInit() {
	}

	protected DateTimeFormat getConfiguredDateFormat() {
		return DateTimeFormat.getFormat("dd.MM.yyyy");
	}

	public void setFormat(DateTimeFormat format) {
		m_Format = format;
		if (isInitialized()) {
			getDatePicker().setFormat(new DateBox.DefaultFormat(format));
		}
	}

	public DateTimeFormat getFormat() {
		return m_Format;
	}

	public void setInitialized(boolean initialized) {
		m_initialized = initialized;
	}

	public boolean isInitialized() {
		return m_initialized;
	}

	public void setWidth(String width) {
		m_width = width;
		if (isInitialized()) {
			getDatePicker().setWidth(getWidth());
		}
	}

	public String getWidth() {
		return m_width;
	}

	protected String getConfiguredWidth() {
		return "100px";
	}

	public Date getValue() {
		return getDatePicker().getValue();
	}

	public void setValue(Date date) {
		getDatePicker().setValue(date);
	}

}
