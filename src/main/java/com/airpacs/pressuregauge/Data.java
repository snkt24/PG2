package com.airpacs.pressuregauge;

public class Data {
	private String instrumentID;
	private String value;
	private String time;
	public String getInstrumentID() {
		return instrumentID;
	}
	public void setInstrumentID(String instrumentID) {
		this.instrumentID = instrumentID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
