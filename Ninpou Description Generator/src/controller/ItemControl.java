package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.Item;
import model.ItemType;

public class ItemControl {

	private static Set<Item> set = new TreeSet<>();
	static {
		ObjectInputStream ois = null;
		try {
			File file = new File("items.data"); 
			FileInputStream fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			Object readObject;
			while ((readObject = ois.readObject()) != null) {
				set.add((Item)readObject);
			}
		} catch(Exception e) { 
			
		} finally {
			try {
				ois.close();
			} catch(Exception e) { }
		} 
	}
	
	public static void save() { 
		ObjectOutputStream oos = null;
		try {
			File file = new File("items.data"); 
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (Item item : getList()) {
				oos.writeObject(item);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch(Exception e) { }
		}
	}
	
	public static void add(Item item) {
		if (item == null) return;
		set.add(item);
	}
	
	public static void remove(Item item) {
		if (item == null) return;
		set.remove(item);
	}
	
	public static List<Item> getList() { 
		List<Item> list = new ArrayList<>(set);
		Collections.sort(list);
		return list;
	}
	
	public static List<Item> getList(ItemType type) {
		List<Item> list = getList();
		List<Item> filteredList = new ArrayList<>();
		for (Item item : list) { 
			if (item.getType() == type) {
				filteredList.add(item);
			}
		}
		return filteredList;
	}
	
}
