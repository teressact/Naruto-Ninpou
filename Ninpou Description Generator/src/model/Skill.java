package model;

import java.io.Serializable;

public class Skill implements Serializable, Comparable<Skill> {

	private static final long serialVersionUID = 1L;

	public static final String REPLACE_WITH_LEVEL = "#";
	
	private String hero = "", name, description;
	private int baseRange, rangeFactor, areaOfEffect;
	private float baseCooldown, cooldownFactor;
	private AreaOfEffectType AoEType;
	private SkillHotkey hotkey;
	private DamageTable damageTable;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBaseRange() {
		return baseRange;
	}

	public void setBaseRange(int baseRange) {
		this.baseRange = baseRange;
	}

	public int getRangeFactor() {
		return rangeFactor;
	}

	public void setRangeFactor(int rangeFactor) {
		this.rangeFactor = rangeFactor;
	}

	public float getBaseCooldown() {
		return baseCooldown;
	}

	public void setBaseCooldown(float baseCooldown) {
		this.baseCooldown = baseCooldown;
	}

	public float getCooldownFactor() {
		return cooldownFactor;
	}

	public void setCooldownFactor(float cooldownFactor) {
		this.cooldownFactor = cooldownFactor;
	}

	public int getAreaOfEffect() {
		return areaOfEffect;
	}

	public void setAreaOfEffect(int areaOfEffect) {
		this.areaOfEffect = areaOfEffect;
	}

	public AreaOfEffectType getAoEType() {
		return AoEType;
	}

	public void setAoEType(AreaOfEffectType aoEType) {
		AoEType = aoEType;
	}

	public SkillHotkey getHotkey() {
		return hotkey;
	}

	public void setHotkey(SkillHotkey hotkey) {
		this.hotkey = hotkey;
	}

	public DamageTable getDamageTable() {
		return damageTable;
	}

	public String getHero() { 
		return hero;
	}
	
	public void setHero(String hero) {
		this.hero = hero;
	}
	
	public Skill() { 
		damageTable = new DamageTable(DamageType.NORMAL);
	}
	
	public String generateGenericName() { 
		if (name == null) return "";
		if (hotkey == null) {
			return String.format("%s - [|cffffcc00Level %s|r]", name, REPLACE_WITH_LEVEL);
		} else {
			return String.format("%s %s - [|cffffcc00Level %s|r]", name, hotkey.getValue(), REPLACE_WITH_LEVEL);
		}
	}
	
	public String generateLearnName() {
		if (name == null) return "";
		if (hotkey == null) {
			return String.format("Learn %s - [|cffffcc00Level %%d|r]", name);
		} else {
			return String.format("Learn %s %s - [|cffffcc00Level %%d|r]", name, hotkey.getValue());
		}
	}
	
	public String generateGenericDescription() {
		StringBuilder string = new StringBuilder();
		string.append(String.format("|cffffcc00Damage:|r %s|n", damageTable));
		if (baseRange <= 120 && rangeFactor == 0) {
			string.append("|cffffcc00Range:|r Meele|n");
		} else {
			if (rangeFactor == 0) {
				string.append(String.format("|cffffcc00Range:|r %d|n", baseRange));
			} else {
				string.append(String.format("|cffffcc00Range:|r %d + %d x Lvl|n", baseRange, rangeFactor));
			}
		}
		if (areaOfEffect == 0) {
			string.append("|cffffcc00Area of Effect:|r Target|n");
		} else {
			if (AoEType == null) {
				string.append(String.format("|cffffcc00Area of Effect:|r %d|n", areaOfEffect));
			} else {
				string.append(String.format("|cffffcc00Area of Effect:|r %d (%s)|n", areaOfEffect, AoEType));
			}
		}
		
		if (baseCooldown == 0 && cooldownFactor == 0) { 
			string.append("|cffffcc00Cooldown:|r Instant|n");
		} else {
			if (cooldownFactor == 0) {
				string.append(String.format("|cffffcc00Cooldown:|r %.1fs|n", baseCooldown, cooldownFactor));
			} else {
				string.append(String.format("|cffffcc00Cooldown:|r %.1fs + %.1fs x Lvl|n", baseCooldown, cooldownFactor));
			}
		}
		if (description != null)
			string.append("|n" + description);
		return string.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AoEType == null) ? 0 : AoEType.hashCode());
		result = prime * result + areaOfEffect;
		result = prime * result + Float.floatToIntBits(baseCooldown);
		result = prime * result + baseRange;
		result = prime * result + Float.floatToIntBits(cooldownFactor);
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((hero == null) ? 0 : hero.hashCode());
		result = prime * result + ((hotkey == null) ? 0 : hotkey.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + rangeFactor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		if (hero == null) {
			if (other.hero != null)
				return false;
		} else if (!hero.equals(other.hero))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (AoEType != other.AoEType)
			return false;
		if (areaOfEffect != other.areaOfEffect)
			return false;
		if (Float.floatToIntBits(baseCooldown) != Float
				.floatToIntBits(other.baseCooldown))
			return false;
		if (baseRange != other.baseRange)
			return false;
		if (Float.floatToIntBits(cooldownFactor) != Float
				.floatToIntBits(other.cooldownFactor))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hotkey != other.hotkey)
			return false;
		
		if (rangeFactor != other.rangeFactor)
			return false;
		return true;
	}

	@Override
	public String toString() { 
		return String.format("(%s) %s", hero, name);
	}

	@Override
	public int compareTo(Skill o) {
		return hero.compareTo(o.getHero()) + (hotkey != null && o.getHotkey() != null ? hotkey.ordinal() - o.getHotkey().ordinal() : 0);
	}
	
}
