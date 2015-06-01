package org.wonderland.dev.levi9.springboot.engine.output;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.wonderland.dev.levi9.springboot.engine.input.BetOffer;
import org.wonderland.dev.levi9.springboot.engine.output.mock.MatchMock;
import org.wonderland.dev.levi9.springboot.engine.output.mock.PlacedBetMock;

public class Match {
	
	private String id;
	private String name;
	private BetOffer offerHome;
	private BetOffer offerAway;
	private PlacedBet placedBetHome;
	private PlacedBet placedBetAway;
	private List<BetOffer> betOffers;

	public Match(String id, String name, BetOffer placedBetHome,
			BetOffer placedBetAway, List<BetOffer> betOffers) {
		super();
		this.id = id;
		this.name = name;
		this.offerHome = placedBetHome;
		this.offerAway = placedBetAway;
	}
	
	public Match(String id, String name, List<BetOffer> betOffers) {
		super();
		this.id = id;
		this.name = name;
		double maxHome = 0.0;
		double maxAway = 0.0;
		for(BetOffer offer : betOffers) {
			if(offer.getOddsAway() > maxAway) {
				this.offerAway = offer;
				maxAway = offer.getOddsAway();
			}
			
			if(offer.getOddsHome() > maxHome) {
				this.offerHome = offer;
				maxHome = offer.getOddsHome();
			}
		}
	}
	
	public MatchMock produceMock() {
		MatchMock mock = new MatchMock();
		return mock;
	}

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

	public BetOffer getOfferHome() {
		return offerHome;
	}

	public void setOfferHome(BetOffer offerHome) {
		this.offerHome = offerHome;
	}

	public BetOffer getOfferAway() {
		return offerAway;
	}

	public void setOfferAway(BetOffer offerAway) {
		this.offerAway = offerAway;
	}

	public List<BetOffer> getBetOffers() {
		return betOffers;
	}

	public void setBetOffers(List<BetOffer> betOffers) {
		this.betOffers = betOffers;
	}

	public double getArbitragePercentage() {
		return getArbitragePercentageTennis();
	}
	
	private double getArbitragePercentageTennis() {
		double oddsHome = offerHome.getOddsHome();
		double oddsAway = offerAway.getOddsAway();
		
		double percentage = (1.0 / oddsHome) * 100.0 + (1.0 / oddsAway) * 100.0;
		return percentage;
	}
	
	public double getProfit(double budget) {
		return budget / (getArbitragePercentage() / 100.0) - budget;
	}
	
	public MatchMock buildMatchMock() {
		MatchMock mock = new MatchMock();
		mock.setId(getId());
		mock.setName(getName());
		List<PlacedBetMock> placedBetsMock = new ArrayList<PlacedBetMock>();
		//placedBetsMock.add(offerHome.buildBetMock());
		//placedBetsMock.add(offerAway.buildBetMock());
		mock.setPlacedBets(placedBetsMock);
		return mock;
	}
	
}
