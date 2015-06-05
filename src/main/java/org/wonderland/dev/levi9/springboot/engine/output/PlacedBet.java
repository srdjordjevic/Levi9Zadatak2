package org.wonderland.dev.levi9.springboot.engine.output;

import org.wonderland.dev.levi9.springboot.engine.input.BetOffer;
import org.wonderland.dev.levi9.springboot.engine.utils.BetType;

public class PlacedBet {

	private BetOffer offer;
	private double bet;
	private BetType betType;

	public PlacedBet(BetOffer offer, double bet, BetType betType) {
		super();
		this.offer = offer;
		this.bet = bet;
		this.betType = betType;
	}

	public BetOffer getOffer() {
		return offer;
	}

	public void setOffer(BetOffer offer) {
		this.offer = offer;
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
