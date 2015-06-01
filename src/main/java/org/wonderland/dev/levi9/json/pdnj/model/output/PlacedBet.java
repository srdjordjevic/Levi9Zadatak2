package org.wonderland.dev.levi9.json.pdnj.model.output;

import org.wonderland.dev.levi9.json.pdnj.utils.BetType;

public class PlacedBet {
	
	private String bookieID;
	private double bet;
	private BetType betType;
	
	public PlacedBet(String bookieID, double bet, BetType betType) {
		super();
		this.bookieID = bookieID;
		this.bet = bet;
		this.betType = betType;
	}
	
	public String getBookieID() {
		return bookieID;
	}
	public void setBookieID(String bookieID) {
		this.bookieID = bookieID;
	}
	public double getBet() {
		return bet;
	}
	public void setBet(double bet) {
		this.bet = bet;
	}
	public BetType getBetType() {
		return betType;
	}
	public void setBetType(BetType betType) {
		this.betType = betType;
	}

	
}
