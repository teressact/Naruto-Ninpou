package test;

import model.BonusTable;
import model.BonusType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BonusTableTest {

	private BonusTable table;
	
	@Before
	public void setUp() { 
		table = new BonusTable();
	}
	
	@Test
	public void test() {
		Assert.assertEquals("|cff32cd32|r", table.generateValues());
		table.add(BonusType.ALL_PROPERTIES, 10, false);
		Assert.assertEquals("|cff32cd32+10 All properties|n|r", table.generateValues());
		table.add(BonusType.JUTSU_REDUCTION, 50, true);
		Assert.assertEquals("|cff32cd32+10 All properties|n+50% Jutsu Reduction|n|r", table.generateValues());
		table.add(BonusType.GENJUTSU, 10, false);
		Assert.assertEquals("|cff32cd32+10 All properties|n+10 Genjutsu|n+50% Jutsu Reduction|n|r", table.generateValues());
		Assert.assertTrue(table.remove(BonusType.ALL_PROPERTIES) != null);
		Assert.assertEquals(2, table.getSize());
		table.add(null, 10, true);
		Assert.assertEquals(2, table.getSize());
	}

}
