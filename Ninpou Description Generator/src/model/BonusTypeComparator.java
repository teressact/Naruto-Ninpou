package model;

import java.util.Comparator;

public class BonusTypeComparator implements Comparator<BonusType> {

	@Override
	public int compare(BonusType o1, BonusType o2) {
		return o1.getId() - o2.getId();
	}

}
