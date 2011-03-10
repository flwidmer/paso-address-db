package ch.paso.address.shared.util;

import java.io.Serializable;

public class SimpleDateTime implements Serializable{
	private static final long serialVersionUID = -8049942974461009717L;
	private static final String S_DATE_SEPARATOR = ".";
	private static final Object S_TIME_SEPARATOR = ":";
	private int m_year;
	private int m_month;
	private int m_day;
	private int m_hour;
	private int m_minute;
	private boolean m_time;
	private boolean m_date;

	public void setYear(int year) {
		m_year = year;
	}

	public int getYear() {
		return m_year;
	}

	public void setMonth(int month) {
		m_month = month;
	}

	public int getMonth() {
		return m_month;
	}

	public void setDay(int day) {
		m_day = day;
	}

	public int getDay() {
		return m_day;
	}

	public SimpleDateTime(int day, int month, int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
		setDate(true);
		setTime(false);
	}

	public void setMinute(int minute) {
		m_minute = minute;
	}

	public int getMinute() {
		return m_minute;
	}

	public void setHour(int hour) {
		m_hour = hour;
	}

	public int getHour() {
		return m_hour;
	}

	public void setDate(boolean date) {
		m_date = date;
	}

	public boolean isDate() {
		return m_date;
	}

	public void setTime(boolean time) {
		m_time = time;
	}

	public boolean isTime() {
		return m_time;
	}

	public SimpleDateTime(int year, int month, int day, int hour, int minute) {
		setDay(day);
		setMonth(month);
		setYear(year);
		setMinute(minute);
		setHour(hour);
		setDate(true);
		setTime(true);
	}
	public SimpleDateTime(){
		
	}
	
	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		if(isDate()){
			result.append(getDay());
			result.append(S_DATE_SEPARATOR);
			result.append(getMonth());
			result.append(S_DATE_SEPARATOR);
			result.append(getYear());
			result.append(" ");
			result.append(getHour());
			result.append(S_TIME_SEPARATOR);
			result.append(getMinute());
		}
		return result.toString();
	}

}
