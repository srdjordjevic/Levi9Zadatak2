package org.wonderland.dev.levi9.json.pdnj.model.output.mock;

public class PlacedBetAwayMock extends PlacedBetMock {
	
	private double betAway;
	
	public PlacedBetAwayMock(String bookieID, double betAway) {
		super(bookieID);
		this.betAway = betAway;
	}

	public double getBetAway() {
		return betAway;
	}

	public void setBetAway(double betAway) {
		this.betAway = betAway;
	}
	
	
}
