package org.wonderland.dev.levi9.springboot.multiengine;

import java.util.ArrayList;
import java.util.List;

import org.wonderland.dev.levi9.springboot.engine.input.BetOffer;
import org.wonderland.dev.levi9.springboot.engine.input.Betting;
import org.wonderland.dev.levi9.springboot.engine.input.Bookie;
import org.wonderland.dev.levi9.springboot.engine.output.Match;
import org.wonderland.dev.levi9.springboot.engine.output.mock.ArbitrageMock;
import org.wonderland.dev.levi9.springboot.engine.output.mock.MatchMock;
import org.wonderland.dev.levi9.springboot.engine.output.mock.PlacedBetMock;

public class Engine {

	public ArbitrageMock findSafeBets(Betting betting) {
		ArbitrageMock mock = new ArbitrageMock();
		betting.reconnectReferences();
		
		return mock;
	}
	
	private MatchMock mockMatch(Match match) {
		MatchMock mock = new MatchMock();
		mock.setId(match.getId());
		mock.setName(match.getName());
		List<PlacedBetMock> bets = new ArrayList<PlacedBetMock>();
		
		mock.setPlacedBets(bets);
		return mock;
	}
}
