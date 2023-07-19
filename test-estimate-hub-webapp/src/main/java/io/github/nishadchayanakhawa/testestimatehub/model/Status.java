package io.github.nishadchayanakhawa.testestimatehub.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
	CREATED("CREATED","Created"),
	ESTIMATED("ESTIMATED","Estimated"),
	REVIEWED("REVIEWED","Reviewed"),
	ALLOCATED("ALLOCATED","Allocated"),
	CLOSED("CLOSED","Closed");
	
	private String code;
	private String displayValue;
	
	Status(String value, String displayValue) {
		this.code=value;
		this.displayValue=displayValue;
	}
	public String getCode() {
		return code;
	}	
	public String getDisplayValue() {
		return displayValue;
	}	
}
