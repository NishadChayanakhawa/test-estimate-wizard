package io.github.nishadchayanakhawa.testestimatehub.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Complexity {
	VERY_LOW("VERY_LOW","01-Very Low"),
	LOW("LOW","02-Low"),
	MEDIUM("MEDIUM","03-Medium"),
	HIGH("HIGH","04-High"),
	VERY_HIGH("VERY_HIGH","05-Very High");
	
	private String code;
	private String displayValue;
	
	Complexity(String value, String displayValue) {
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
