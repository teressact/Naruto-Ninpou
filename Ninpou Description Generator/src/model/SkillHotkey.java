package model;

import java.io.Serializable;

public enum SkillHotkey implements Serializable {

	Q, W, E, R, T;
	
	public String getValue() { 
		return "[|cffffcc00" + this.name() + "|r]";
	}
	
}
