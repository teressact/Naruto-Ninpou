package model;

import java.io.Serializable;

public class DamageTable implements Serializable {

	private static final long serialVersionUID = 1L;
	private float baseDamage, damageFactor;
	private DamageType type;
	private BonusType multiplier;
	
	public DamageTable(DamageType type) {
		this.type = type;
	}
	
	public float getBaseDamage() {
		return baseDamage;
	}
	public void setBaseDamage(float baseDamage) {
		this.baseDamage = baseDamage;
	}
	public float getDamageFactor() {
		return damageFactor;
	}
	public void setDamageFactor(float damageFactor) {
		this.damageFactor = damageFactor;
	}
	public DamageType getType() {
		return type;
	}
	public void setType(DamageType type) {
		this.type = type;
	}
	public BonusType getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(BonusType multiplier) {
		this.multiplier = multiplier;
	}
	
	@Override
	public String toString() { 
		String strBaseDamage;
		if (baseDamage == 0) {
			strBaseDamage = "";
		} else {
			strBaseDamage = String.format("%.1f + ", baseDamage);
		}
		String strDamageFactor;
		if (damageFactor == 0) {
			return "0";
		} else if (damageFactor == 1) {
			strDamageFactor = "";
		} else {
			strDamageFactor = String.format("%.1f x ", damageFactor);
		}
		String strMultiplier;
		if (multiplier == null) {
			strMultiplier = "";
		} else {
			strMultiplier = multiplier.toString() + " x ";
		}
		return strBaseDamage + strDamageFactor + strMultiplier + "Lvl (|cff87ceeb" + type + "|r)";
	}
	
}
