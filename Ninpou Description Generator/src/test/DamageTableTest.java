package test;

import model.BonusType;
import model.DamageTable;
import model.DamageType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DamageTableTest {

	private DamageTable type;
	
	@Before
	public void setUp() { 
		type = new DamageTable(DamageType.MAGIC);
	}
	
	@Test
	public void test() {
		Assert.assertEquals("0", type.toString());
		type.setDamageFactor(80);
		Assert.assertEquals("80,0 x Lvl (|cff87ceebMagic|r)", type.toString());
		type.setBaseDamage(100);
		Assert.assertEquals("100,0 + 80,0 x Lvl (|cff87ceebMagic|r)", type.toString());
		type.setMultiplier(BonusType.NINJUTSU);
		Assert.assertEquals("100,0 + 80,0 x Ninjutsu x Lvl (|cff87ceebMagic|r)", type.toString());
		type.setBaseDamage(0);
		type.setDamageFactor(1);
		Assert.assertEquals("Ninjutsu x Lvl (|cff87ceebMagic|r)", type.toString());
		type.setMultiplier(null);
		Assert.assertEquals("Lvl (|cff87ceebMagic|r)", type.toString());
		type.setBaseDamage(1000);
		Assert.assertEquals("1000,0 + Lvl (|cff87ceebMagic|r)", type.toString());
		type.setDamageFactor(1);
		Assert.assertEquals("1000,0 + Lvl (|cff87ceebMagic|r)", type.toString());
		type.setType(DamageType.CHAOS);
		type.setBaseDamage(0);
		type.setDamageFactor(1000);
		Assert.assertEquals("1000,0 x Lvl (|cff87ceebChaos|r)", type.toString());
	}

}
