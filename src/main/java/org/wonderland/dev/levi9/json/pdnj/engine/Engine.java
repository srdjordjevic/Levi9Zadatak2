package org.wonderland.dev.levi9.json.pdnj.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.wonderland.dev.levi9.json.pdnj.model.input.BetOffer;
import org.wonderland.dev.levi9.json.pdnj.model.input.Betting;
import org.wonderland.dev.levi9.json.pdnj.model.input.Bookie;
import org.wonderland.dev.levi9.json.pdnj.model.output.mock.ArbitrageMock;
import org.wonderland.dev.levi9.json.pdnj.model.output.mock.MatchMock;
import org.wonderland.dev.levi9.json.pdnj.model.output.mock.PlacedBetAwayMock;
import org.wonderland.dev.levi9.json.pdnj.model.output.mock.PlacedBetHomeMock;
import org.wonderland.dev.levi9.json.pdnj.model.output.mock.PlacedBetMock;
import org.wonderland.dev.levi9.json.pdnj.utils.Constants;
import org.wonderland.dev.levi9.json.pdnj.utils.JsonProcessor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class Engine {

	private JsonProcessor jsonProcessor;
	private String inputPath;
	private String outputPath;
	private Multimap<String, BetOffer> betOffers;
	
	public Engine(JsonProcessor jsonProcessor, String inputPath, String outputPath) {
		this.jsonProcessor = jsonProcessor;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}
	
	public Engine(JsonProcessor jsonProcessor, String inputPath) {
		this.jsonProcessor = jsonProcessor;
		this.inputPath = inputPath;
		outputPath = Constants.DEFAULT_OUTPUT_FILE_NAME;
	}
	
	private void connectReferences(Betting betting) {
		for(Bookie bookie : betting.getBookies()) {
			for(BetOffer offer : bookie.getBetOffers()) {
				offer.setBookie(bookie);
				betOffers.put(offer.getId(), offer);
			}
			bookie.setBetting(betting);
		}
	}
	
	public void processData() {
		try {
			Betting betting = jsonProcessor.readJson(inputPath, Betting.class);
			betOffers = HashMultimap.create();
			connectReferences(betting);
			ArbitrageMock arbitrage = calculateArbitrage(betting);
			jsonProcessor.writeJson(outputPath, arbitrage);
			
		} catch (JsonParseException e) {
			System.err.println("Parsing error");
		} catch (JsonMappingException e) {
			System.err.println("Mapping error");
		} catch (IOException e) {
			System.err.println("IO error");
		}
		
		
	}

	private double getArbitragePercentageTennis(double homeOdds, double awayOdds) {
		double percentage = (1.0 / homeOdds) * 100.0 + (1.0 / awayOdds) * 100.0;
		return percentage;
	}
	
	private ArbitrageMock calculateArbitrage(Betting betting) {
		ArbitrageMock arbitrage = new ArbitrageMock();
		double calculatedProfit = 0.0;
		List<String> candidateMatchIDs = new ArrayList<String>();
		
		for (String key : betOffers.keySet()) {
			if(betOffers.get(key).size() > 1) {
				candidateMatchIDs.add(key);
			}
		}
		
		List<MatchMock> matches = populateMatches(betting, candidateMatchIDs);
		calculatedProfit = calculateProfit(betting);
		arbitrage.setCalculatedProfit(calculatedProfit);
		arbitrage.setMatches(matches);
		return arbitrage;
	}
	
	private double calculateProfit(Betting betting) {
		
		return 0;
	}

	private List<MatchMock> populateMatches(Betting betting, List<String> candidateIDs) {
		List<MatchMock> matches = new ArrayList<MatchMock>();
		
		for(String candidateId : candidateIDs) {
			MatchMock match = new MatchMock();
			String matchName = "";
			List<PlacedBetMock> placedBets = new ArrayList<PlacedBetMock>();
			Set<BetOffer> offers = (Set<BetOffer>) betOffers.get(candidateId);
			PlacedBetHomeMock maxBetHome = new PlacedBetHomeMock("", 0.0);
			PlacedBetAwayMock maxBetAway = new PlacedBetAwayMock("", 0.0);
			for(BetOffer offer : offers) {
				if(maxBetHome.getBetHome() < offer.getOddsHome()) {
					maxBetHome.setBookieID(offer.getBookie().getId());
					maxBetHome.setBetHome(offer.getOddsHome());
					matchName = offer.getName();
				}
				if(maxBetAway.getBetAway() < offer.getOddsAway()) {
					maxBetAway.setBookieID(offer.getBookie().getId());
					maxBetAway.setBetAway(offer.getOddsAway());
					matchName = offer.getName();
				}
			}
			
			if(getArbitragePercentageTennis(maxBetHome.getBetHome(), maxBetAway.getBetAway()) < 100.0) {
				placedBets.add(maxBetHome);
				placedBets.add(maxBetAway);
				
				match.setPlacedBets(placedBets);
				
				match.setName(matchName);
				match.setId(candidateId);
				
				matches.add(match);
			}
			
			
		}
		
		return matches;
	}
}
