package org.wonderland.dev.levi9.springboot.engine.input;

import java.util.List;

public class Betting {

	private double budget;
	private double desiredProfit;
	
	private List<Bookie> bookies;

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getDesiredProfit() {
		return desiredProfit;
	}

	public void setDesiredProfit(double desiredProfit) {
		this.desiredProfit = desiredProfit;
	}

	public List<Bookie> getBookies() {
		return bookies;
	}

	public void setBookies(List<Bookie> bookies) {
		this.bookies = bookies;
	}

	@Override
	public String toString() {
		return "Betting [budget = " + budget + ", desiredProfit = " + desiredProfit
				+ ", bookies = " + 
				bookies
				+ "]";
	}
	
	public Betting reconnectReferences() {
		for(Bookie bookie : getBookies()) {
			for(BetOffer offer : bookie.getBetOffers()) {
				offer.setBookie(bookie);
			}
			bookie.setBetting(this);
		}
		return this;
	}
	
}
