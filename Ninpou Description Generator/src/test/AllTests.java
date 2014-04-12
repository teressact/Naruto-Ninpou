package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BonusTableTest.class, DamageTableTest.class, ItemTest.class,
		SkillTableTest.class, SkillTest.class })
public class AllTests {

}
