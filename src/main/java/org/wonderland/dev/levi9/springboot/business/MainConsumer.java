package org.wonderland.dev.levi9.springboot.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.wonderland.dev.levi9.springboot.ClientPOSTRequest;
import org.wonderland.dev.levi9.springboot.engine.input.Betting;
import org.wonderland.dev.levi9.springboot.engine.input.Bookie;

public class MainConsumer implements Consumer {
	
	RestTemplate restTemplate;
	
	public MainConsumer() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public Betting consume(ClientPOSTRequest req) {
		Betting betting = new Betting();
		List<Bookie> bookies = new ArrayList<Bookie>();
		// TODO Auto-generated method stub
		for(String url : req.getServicesUrls()) {
			Bookie bookie = fetchBookieFromUrl(url.trim());
			bookie.setBetting(betting);
			bookies.add(bookie);
		}
		betting.setBudget(req.getBudget());
		betting.setDesiredProfit(req.getProfit());
		betting.setBookies(bookies);
		return betting;
	}
	
	private Bookie fetchBookieFromUrl(String url) {
		return restTemplate.getForObject(url, Bookie.class);
	}
}
