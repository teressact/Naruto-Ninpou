package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class SkillTable implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String color = "|cff87ceeb";
	private HashMap<String, Object[]> table;
	
	public SkillTable() { 
		table = new HashMap<>();
	}
	
	public void add(String name, String description, SkillType type) {
		table.put(name, new Object[]{description, type});
	}
	
	public Object[] remove(String name) {
		return table.remove(name);
	}

	public List<Object[]> getAllValues() { 
		List<Object[]> values = new ArrayList<>();
		List<String> keyList = new ArrayList<>(table.keySet());
		Collections.sort(keyList);
		for (String key : keyList) {
			String description = (String)table.get(key)[0];
			SkillType type = (SkillType)table.get(key)[1];
			values.add(new Object[]{key, description, type});
		}
		return values;
	}
	
	public String generateValues() { 
		StringBuilder values = new StringBuilder(color);
		List<String> list = new ArrayList<>(table.keySet());
		Collections.sort(list);
		for (String key : list) {
			String description = (String)table.get(key)[0];
			SkillType type = (SkillType)table.get(key)[1];
			values.append(String.format("- %s (%s):|n     %s|n", key, type, description));
		}
		values.append("|r");
		return values.toString();
	}
	
}
