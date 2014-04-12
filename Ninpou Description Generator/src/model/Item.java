package model;

import java.io.Serializable;

public class Item implements Comparable<Item>, Serializable {

	private static final long serialVersionUID = 1L;
	private String name, description, hotkey;
	private ItemType type;
	private BonusTable bonusTable;
	private SkillTable skillTable;
	
	public Item(String name, String description, String hotkey, ItemType type) {
		this();
		this.name = name;
		this.description = description;
		this.hotkey = hotkey;
		this.type = type;
	}
	
	public Item() { 
		bonusTable = new BonusTable();
		skillTable = new SkillTable();
	}

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

	public String getHotkey() {
		return hotkey;
	}

	public void setHotkey(String hotkey) {
		this.hotkey = hotkey;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public BonusTable getBonusTable() {
		return bonusTable;
	}

	public SkillTable getSkillTable() {
		return skillTable;
	}
	
	public String generateDescription() { 
		StringBuilder string = new StringBuilder();
		if (description != null)
			string.append(description + "|n");
		string.append(bonusTable.generateValues());
		string.append(skillTable.generateValues());
		if (type != null)
			string.append(type.getClassification()); 
		return string.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((hotkey == null) ? 0 : hotkey.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hotkey == null) {
			if (other.hotkey != null)
				return false;
		} else if (!hotkey.equals(other.hotkey))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	public String generateBuyDescription() { 
		if (name == null) return "";
		StringBuilder string = new StringBuilder();
		if (hotkey != null) {
			string.append(String.format("Buy %s - [|cffffcc00%s|r]", name, hotkey));
		} else {
			string.append(String.format("Buy %s", name));
		}
		return string.toString();
	}
	
	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Item o) {
		return this.name.compareTo(o.getName());
	}
	
}
