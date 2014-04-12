package model;

public enum AreaOfEffectType {

	CIRCULAR, LINE, FAN;
	
	@Override
	public String toString() { 
		switch(this) {
		case CIRCULAR:
			return "Circular";
		case LINE:
			return "Line";
		case FAN:
			return "Fan";
		default:
			return "";
		}
	}
}
