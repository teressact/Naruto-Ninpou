package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class BonusTable implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String color = "|cff32cd32";
	private HashMap<BonusType, Object[]> table;
	
	public BonusTable() { 
		table = new HashMap<>();
	}
	
	public void add(BonusType type, int value, boolean isPercentage) {
		if (type == null) return;
		table.put(type, new Object[]{value, isPercentage});
	}
	
	public Object[] remove(BonusType type) {
		return table.remove(type);
	}
	
	public List<Object[]> getAllValues() { 
		List<Object[]> values = new ArrayList<>();
		List<BonusType> keyList = new ArrayList<>(table.keySet());
		Collections.sort(keyList, new BonusTypeComparator());
		for (BonusType key : keyList) {
			int value = (int)table.get(key)[0];
			boolean isPercentage = (boolean)table.get(key)[1];
			values.add(new Object[]{key, value, isPercentage});
		}
		return values;
	}
	
	public String generateValues() { 
		StringBuilder values = new StringBuilder(color);
		List<BonusType> list = new ArrayList<>(table.keySet());
		Collections.sort(list, new BonusTypeComparator());
		for (BonusType key : list) {
			int value = (int)table.get(key)[0];
			boolean isPercentage = (boolean)table.get(key)[1];
			if (isPercentage) {
				values.append(String.format("+%d%% %s|n", value, key));
			} else {
				values.append(String.format("+%d %s|n", value, key));
			}
		}
		values.append("|r");
		return values.toString();
	}
	
	public int getSize() { 
		return table.size();
	}
	
}
