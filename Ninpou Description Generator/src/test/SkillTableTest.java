package test;

import model.SkillTable;
import model.SkillType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SkillTableTest {

	private SkillTable table;
	
	@Before
	public void setUp() { 
		table = new SkillTable();
	}
	
	@Test
	public void test() {
		Assert.assertEquals("|cff87ceeb|r", table.generateValues());
		table.add("Three-tails Aura", "Reflects meele attacks", SkillType.PASSIVE);
		Assert.assertEquals("|cff87ceeb- Three-tails Aura (Passive):|n     Reflects meele attacks|n|r", table.generateValues());
		table.add("Barney Power", "Invokes powerful Barney", SkillType.ACTIVE);
		Assert.assertEquals("|cff87ceeb- Barney Power (Active):|n     Invokes powerful Barney|n- Three-tails Aura (Passive):|n     Reflects meele attacks|n|r", table.generateValues());
	}

}
