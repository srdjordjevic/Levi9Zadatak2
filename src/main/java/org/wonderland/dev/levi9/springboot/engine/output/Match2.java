package org.wonderland.dev.levi9.springboot.engine.output;

import java.util.List;

import org.wonderland.dev.levi9.springboot.engine.input.BetOffer;
import org.wonderland.dev.levi9.springboot.engine.utils.BetType;

public class Match2 {
	private String id;
	private String name;
	private List<BetOffer> betOffers;
	private double budget;
	private double remainingBudget;
	private BetOffer bestOfferHome;
	private BetOffer bestOfferAway;
	private double matchArbitragePercentage;
	private double homeArbitragePercentage;
	private double awayArbitragePercentage;
	private PlacedBet betHome;
	private PlacedBet betAway;
	
	public Match2(String id, String name, List<BetOffer> betOffers, double budget) {
		this.id = id;
		this.name = name;
		this.betOffers = betOffers;
		this.budget = budget;
		init();
	}
	
	private void init() {
		bestOfferHome = findBestOffer(BetType.HOME);
		bestOfferAway = findBestOffer(BetType.AWAY);
		matchArbitragePercentage = calculateMatchArbitragePercentage();
		homeArbitragePercentage = calculateOfferArbitragePercentage(BetType.HOME);
		awayArbitragePercentage = calculateOfferArbitragePercentage(BetType.AWAY);
		betHome = createPlacedBet(BetType.HOME);
		betAway = createPlacedBet(BetType.AWAY);
		
	}
	
	private PlacedBet createPlacedBet(BetType betType) {
		return new PlacedBet(getBestOffer(betType), calculateBet(betType), betType);	 
	}
	
	private double calculateBet(BetType betType) {
		BetOffer offer = getBestOffer(betType);
		double betBudget = offer.getMaxBet() < budget ? offer.getMaxBet() : budget;
		return (betBudget * (getArbitragePercentage(betType) / 100.0) / (matchArbitragePercentage / 100.0));
	}

	private double getArbitragePercentage(BetType betType) {
		switch(betType) {
		case HOME:
			return homeArbitragePercentage;
		case AWAY:
			return awayArbitragePercentage;
		}
		return 0.0;
	}
	
	private BetOffer getBestOffer(BetType betType) {
		switch(betType) {
		case HOME:
			return bestOfferHome;
		case AWAY:
			return bestOfferAway;
		}
		return null;
	}

	private double calculateOfferArbitragePercentage(BetType betType) {
		// (1/1.36)*100=73.529%
		return 0.0;
	}

	private double calculateMatchArbitragePercentage() {
		return 0.0;
	}

	private BetOffer findBestOffer(BetType betType) {
		BetOffer bestOffer = null;
		double offerOdds = 0.0;
		for(BetOffer offer : betOffers) {
			switch(betType) {
			case HOME:
				if(offer.getOddsHome() > offerOdds) {
					bestOffer = offer;
					offerOdds = offer.getOddsHome();
				}
				break;
			case AWAY:
				if(offer.getOddsAway() > offerOdds) {
					bestOffer = offer;
					offerOdds = offer.getOddsAway();
				}
				break;
			}
		}
		
		return bestOffer;
	}
	
	public double profitEstimation() {
		return 0.0;
	}

	public boolean isProfitable() {
		return matchArbitragePercentage < 100.0;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public PlacedBet getBetHome() {
		return betHome;
	}

	public PlacedBet getBetAway() {
		return betAway;
	}
}
