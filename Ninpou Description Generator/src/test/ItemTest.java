package test;

import model.BonusType;
import model.Item;
import model.ItemType;
import model.SkillType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item item;
	
	@Before
	public void setUp() { 
		item = new Item();
	}
	
	@Test
	public void test() {
		Assert.assertEquals("|cff32cd32|r|cff87ceeb|r", item.generateDescription());
		Assert.assertEquals("", item.generateBuyDescription());
		item.setName("Sanbi's Skin");
		Assert.assertEquals("Buy Sanbi's Skin", item.generateBuyDescription());
		item.setHotkey("R");
		Assert.assertEquals("Buy Sanbi's Skin - [|cffffcc00R|r]", item.generateBuyDescription());
		item.setDescription("A legendary armor made from Sanbi's skin");
		Assert.assertEquals("A legendary armor made from Sanbi's skin|n|cff32cd32|r|cff87ceeb|r", item.generateDescription());
		item.getBonusTable().add(BonusType.ARMOR, 35, false);
		Assert.assertEquals("A legendary armor made from Sanbi's skin|n|cff32cd32+35 Armor|n|r|cff87ceeb|r", item.generateDescription());
		item.getBonusTable().add(BonusType.DAMAGE, 100, false);
		Assert.assertEquals("A legendary armor made from Sanbi's skin|n|cff32cd32+100 Damage|n+35 Armor|n|r|cff87ceeb|r", item.generateDescription());
		item.getSkillTable().add("Three-tails Aura", "Reflects meele attacks", SkillType.PASSIVE);
		Assert.assertEquals("A legendary armor made from Sanbi's skin|n|cff32cd32+100 Damage|n+35 Armor|n|r|cff87ceeb- Three-tails Aura (Passive):|n     Reflects meele attacks|n|r", item.generateDescription());
		item.setType(ItemType.ARMOR);
		Assert.assertEquals("A legendary armor made from Sanbi's skin|n|cff32cd32+100 Damage|n+35 Armor|n|r|cff87ceeb- Three-tails Aura (Passive):|n     Reflects meele attacks|n|r|cffff0000Classification: Armor|r", item.generateDescription());
	}

}
