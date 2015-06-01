package org.wonderland.dev.levi9.json.pdnj.model.input;

import org.wonderland.dev.levi9.json.pdnj.model.output.mock.PlacedBetMock;

public class BetOffer {
	
	private String id;
	private String name;
	private double oddsHome;
	private double oddsAway;
	private double maxBet;
	private Bookie bookie;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getOddsHome() {
		return oddsHome;
	}
	public void setOddsHome(double oddsHome) {
		this.oddsHome = oddsHome;
	}
	public double getOddsAway() {
		return oddsAway;
	}
	public void setOddsAway(double oddsAway) {
		this.oddsAway = oddsAway;
	}
	public double getMaxBet() {
		return maxBet;
	}
	public void setMaxBet(double maxBet) {
		this.maxBet = maxBet;
	}
	
	public Bookie getBookie() {
		return bookie;
	}
	public void setBookie(Bookie bookie) {
		this.bookie = bookie;
	}
	@Override
	public String toString() {
		return "BetOffer [id = " + id + ", name = " + name + ", oddsHome = "
				+ oddsHome + ", oddsAway = " + oddsAway + ", maxBet = " + maxBet
				+ "]";
	}	
	
}
