package org.wonderland.dev.levi9.json.pdnj.model.output.mock;

public class PlacedBetHomeMock extends PlacedBetMock {

	private double betHome;
	
	public PlacedBetHomeMock(String bookieID, double betHome) {
		super(bookieID);
		this.betHome = betHome;
	}

	public double getBetHome() {
		return betHome;
	}

	public void setBetHome(double betHome) {
		this.betHome = betHome;
	}
	
	
	
}
