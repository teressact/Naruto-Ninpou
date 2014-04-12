package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Skill;

public class SkillControl {

	private static List<Skill> set = new ArrayList<>();
	static {
		ObjectInputStream ois = null;
		try {
			File file = new File("skills.data"); 
			FileInputStream fis = new FileInputStream(file);
			ois = new ObjectInputStream(fis);
			Object readObject;
			while ((readObject = ois.readObject()) != null) {
				set.add((Skill)readObject);
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
			File file = new File("skills.data"); 
			FileOutputStream fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			for (Skill skill : getList()) {
				oos.writeObject(skill);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch(Exception e) { }
		}
	}
	
	public static void add(Skill skill) {
		if (skill == null) return;
		set.add(skill);
	}
	
	public static void remove(Skill skill) {
		if (skill == null) return;
		set.remove(skill);
	}
	
	public static List<Skill> getList() { 
		List<Skill> list = new ArrayList<>(set);
		Collections.sort(list);
		return list;
	}
	
	public static List<Skill> getList(String prefix) {
		List<Skill> list = getList();
		List<Skill> filteredList = new ArrayList<>();
		for (Skill skill : list) { 
			if (prefix.equals("") || skill.toString().toLowerCase().contains(prefix)) {
				filteredList.add(skill);
			}
		}
		return filteredList;
	}
	
}
