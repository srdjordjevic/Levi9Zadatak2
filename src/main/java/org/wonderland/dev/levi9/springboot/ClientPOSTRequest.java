package org.wonderland.dev.levi9.springboot;

import java.util.ArrayList;
import java.util.List;

public class ClientPOSTRequest {
	private double budget;
	private double profit;
	private List<String> servicesUrls;
	
	public ClientPOSTRequest() {
		this.budget = 0.0;
		this.profit = 0.0;
		servicesUrls = new ArrayList<String>();
	}

	public ClientPOSTRequest(double budget, double profit,
			List<String> servicesUrls) {
		super();
		this.budget = budget;
		this.profit = profit;
		this.servicesUrls = servicesUrls;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public List<String> getServicesUrls() {
		return servicesUrls;
	}

	public void setServicesUrls(List<String> servicesUrls) {
		this.servicesUrls = servicesUrls;
	}

	@Override
	public String toString() {
		return "ClientPOSTRequest [budget=" + budget + ", profit=" + profit
				+ ", servicesUrls=" + servicesUrls + "]";
	}
	
	
	
}
