package org.wonderland.dev.levi9.springboot.engine.input;

import java.util.List;

public class Bookie {

	private String id;
	private String name;
	private List<BetOffer> betOffers;
	private Betting betting;
	
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
	public List<BetOffer> getBetOffers() {
		return betOffers;
	}
	public void setBetOffers(List<BetOffer> betOffers) {
		this.betOffers = betOffers;
	}
	
	public Betting getBetting() {
		return betting;
	}
	public void setBetting(Betting betting) {
		this.betting = betting;
	}
	
	@Override
	public String toString() {
		return "Bookie [id = " + id + ", name = " + name + ", betOffers = "
				+ betOffers + "]";
	}
	
}
