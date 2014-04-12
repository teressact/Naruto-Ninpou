package model;

import java.io.Serializable;

public enum DamageType implements Serializable {

	MAGIC, NORMAL, CHAOS;
	
	@Override
	public String toString() { 
		switch(this) {
		case CHAOS:
			return "Chaos";
		case MAGIC:
			return "Magic";
		case NORMAL:
			return "Normal";
		default:
			return "";
		}
	}
	
}
