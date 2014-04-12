package model;

import java.io.Serializable;

public enum SkillType implements Serializable {

	ACTIVE, PASSIVE;
	
	@Override
	public String toString() { 
		switch (this) {
		case ACTIVE:
			return "Active";
		case PASSIVE:
			return "Passive";
		default:
			return "";
		}
	}
	
}
