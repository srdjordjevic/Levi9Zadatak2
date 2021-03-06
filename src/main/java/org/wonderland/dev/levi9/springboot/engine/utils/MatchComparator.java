package org.wonderland.dev.levi9.springboot.engine.utils;

import java.util.Comparator;

import org.wonderland.dev.levi9.springboot.engine.output.Match;

public class MatchComparator implements Comparator<Match> {

	@Override
	public int compare(Match match1, Match match2) {
		if (match1.getArbitragePercentage() < match2.getArbitragePercentage()) return -1;
        if (match1.getArbitragePercentage() > match2.getArbitragePercentage()) return 1;
        return 0;
	}

}
