package model;

import java.io.Serializable;

public enum ItemType implements Serializable {

	WEAPON, ARMOR, NINJA_WEAPON, SUPPORT, MISCELLANEOUS;
	
	@Override
	public String toString() { 
		switch(this) {
		case WEAPON:
			return "Weapon";
		case ARMOR:
			return "Armor";
		case NINJA_WEAPON:
			return "Ninja Weapon";
		case SUPPORT:
			return "Support";
		case MISCELLANEOUS:
			return "Miscellaneous";
		default:
			return "";
		}
	}
	
	public String getClassification() { 
		switch (this) {
		case WEAPON:
			return "|cffff0000Classification: Weapon|r";
		case ARMOR:
			return "|cffff0000Classification: Armor|r";
		case NINJA_WEAPON:
			return "|cffff0000Classification: Ninja Weapon|r";
		case SUPPORT:
			return "|cffff0000Classification: Support|r";
		case MISCELLANEOUS:
			return "|cffff0000Classification: Miscellaneous|r";
		default:
			return "";
		}
	}
	
}
