package org.wonderland.dev.levi9.springboot.engine.output.mock;

import java.util.List;

public class ArbitrageMock {
	private double calculatedProfit;
	private List<MatchMock> matches;
	
	public double getCalculatedProfit() {
		return calculatedProfit;
	}
	public void setCalculatedProfit(double calculatedProfit) {
		this.calculatedProfit = calculatedProfit;
	}
	public List<MatchMock> getMatches() {
		return matches;
	}
	public void setMatches(List<MatchMock> matches) {
		this.matches = matches;
	}
}
