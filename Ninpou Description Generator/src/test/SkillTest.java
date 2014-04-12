package test;

import model.AreaOfEffectType;
import model.BonusType;
import model.Skill;
import model.SkillHotkey;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SkillTest {

	private Skill skill;
	
	@Before
	public void setUp() { 
		skill = new Skill();
	}
	
	@Test
	public void test() {
		Assert.assertEquals("|cffffcc00Damage:|r 0|n|cffffcc00Range:|r Meele|n|cffffcc00Area of Effect:|r Target|n|cffffcc00Cooldown:|r Instant|n", skill.generateGenericDescription());
		Assert.assertEquals("", skill.generateGenericName());
		Assert.assertEquals("", skill.generateLearnName());
		skill.setName("Rasengan");
		Assert.assertEquals("Rasengan - [|cffffcc00Level #|r]", skill.generateGenericName());
		Assert.assertEquals("Learn Rasengan - [|cffffcc00Level %d|r]", skill.generateLearnName());
		skill.setHotkey(SkillHotkey.Q);
		Assert.assertEquals("Rasengan [|cffffcc00Q|r] - [|cffffcc00Level #|r]", skill.generateGenericName());
		Assert.assertEquals("Learn Rasengan [|cffffcc00Q|r] - [|cffffcc00Level %d|r]", skill.generateLearnName());
		skill.getDamageTable().setDamageFactor(1);
		skill.getDamageTable().setMultiplier(BonusType.TAIJUTSU);
		Assert.assertEquals("|cffffcc00Damage:|r Taijutsu x Lvl (|cff87ceebNormal|r)|n|cffffcc00Range:|r Meele|n|cffffcc00Area of Effect:|r Target|n|cffffcc00Cooldown:|r Instant|n", skill.generateGenericDescription());
		skill.setBaseRange(500);
		Assert.assertEquals("|cffffcc00Damage:|r Taijutsu x Lvl (|cff87ceebNormal|r)|n|cffffcc00Range:|r 500|n|cffffcc00Area of Effect:|r Target|n|cffffcc00Cooldown:|r Instant|n", skill.generateGenericDescription());
		skill.setRangeFactor(100);
		Assert.assertEquals("|cffffcc00Damage:|r Taijutsu x Lvl (|cff87ceebNormal|r)|n|cffffcc00Range:|r 500 + 100 x Lvl|n|cffffcc00Area of Effect:|r Target|n|cffffcc00Cooldown:|r Instant|n", skill.generateGenericDescription());
		skill.setBaseCooldown(15);
		Assert.assertEquals("|cffffcc00Damage:|r Taijutsu x Lvl (|cff87ceebNormal|r)|n|cffffcc00Range:|r 500 + 100 x Lvl|n|cffffcc00Area of Effect:|r Target|n|cffffcc00Cooldown:|r 15,0s|n", skill.generateGenericDescription());
		skill.setCooldownFactor(0.3f);
		Assert.assertEquals("|cffffcc00Damage:|r Taijutsu x Lvl (|cff87ceebNormal|r)|n|cffffcc00Range:|r 500 + 100 x Lvl|n|cffffcc00Area of Effect:|r Target|n|cffffcc00Cooldown:|r 15,0s + 0,3s x Lvl|n", skill.generateGenericDescription());
		skill.setAreaOfEffect(500);
		Assert.assertEquals("|cffffcc00Damage:|r Taijutsu x Lvl (|cff87ceebNormal|r)|n|cffffcc00Range:|r 500 + 100 x Lvl|n|cffffcc00Area of Effect:|r 500|n|cffffcc00Cooldown:|r 15,0s + 0,3s x Lvl|n", skill.generateGenericDescription());
		skill.setAoEType(AreaOfEffectType.LINE);
		Assert.assertEquals("|cffffcc00Damage:|r Taijutsu x Lvl (|cff87ceebNormal|r)|n|cffffcc00Range:|r 500 + 100 x Lvl|n|cffffcc00Area of Effect:|r 500 (Line)|n|cffffcc00Cooldown:|r 15,0s + 0,3s x Lvl|n", skill.generateGenericDescription());
		skill.setDescription("A powerful jutsu");
		Assert.assertEquals("|cffffcc00Damage:|r Taijutsu x Lvl (|cff87ceebNormal|r)|n|cffffcc00Range:|r 500 + 100 x Lvl|n|cffffcc00Area of Effect:|r 500 (Line)|n|cffffcc00Cooldown:|r 15,0s + 0,3s x Lvl|n|nA powerful jutsu", skill.generateGenericDescription());
	}

}
