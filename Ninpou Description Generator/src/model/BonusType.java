package model;

import java.io.Serializable;

public enum BonusType implements Serializable {

	LIFE, CHAKRA, DAMAGE, ALL_PROPERTIES, TAIJUTSU, NINJUTSU, GENJUTSU, ARMOR, JUTSU_REDUCTION, MOVEMENT_SPEED;
	
	@Override
	public String toString() { 
		switch(this) {
		case ALL_PROPERTIES:
			return "All properties";
		case ARMOR:
			return "Armor";
		case CHAKRA:
			return "Chakra";
		case DAMAGE:
			return "Damage";
		case GENJUTSU:
			return "Genjutsu";
		case JUTSU_REDUCTION:
			return "Jutsu Reduction";
		case LIFE:
			return "Life";
		case MOVEMENT_SPEED:
			return "Movement Speed";
		case NINJUTSU:
			return "Ninjutsu";
		case TAIJUTSU:
			return "Taijutsu";
		default:
			return "";
		}
	}
	
	public int getId() { 
		switch(this) {
		case LIFE:
			return 0;
		case CHAKRA:
			return 1;
		case DAMAGE:
			return 2;
		case ALL_PROPERTIES:
			return 3;
		case TAIJUTSU:
			return 4;
		case NINJUTSU:
			return 5;
		case GENJUTSU:
			return 6;
		case ARMOR:
			return 7;
		case JUTSU_REDUCTION:
			return 8;
		case MOVEMENT_SPEED:
			return 9;
		default:
			return 0;
		}
	}
	
}
