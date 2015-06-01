package org.wonderland.dev.levi9.json.pdnj.engine2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.wonderland.dev.levi9.json.pdnj.model.input.BetOffer;
import org.wonderland.dev.levi9.json.pdnj.model.input.Betting;
import org.wonderland.dev.levi9.json.pdnj.model.input.Bookie;
import org.wonderland.dev.levi9.json.pdnj.model.output.Match;
import org.wonderland.dev.levi9.json.pdnj.model.output.mock.ArbitrageMock;
import org.wonderland.dev.levi9.json.pdnj.model.output.mock.MatchMock;
import org.wonderland.dev.levi9.json.pdnj.utils.Constants;
import org.wonderland.dev.levi9.json.pdnj.utils.JsonProcessor;
import org.wonderland.dev.levi9.json.pdnj.utils.MatchComparator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Engine {

    private final JsonProcessor jsonProcessor;

    private final String inputPath;

    private final String outputPath;

    public Engine(final JsonProcessor jsonProcessor, final String inputPath, final String outputPath) {
        this.jsonProcessor = jsonProcessor;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public Engine(final JsonProcessor jsonProcessor, final String inputPath) {
        this.jsonProcessor = jsonProcessor;
        this.inputPath = inputPath;
        this.outputPath = Constants.DEFAULT_OUTPUT_FILE_NAME;
    }

    public void processData() throws JsonParseException, JsonMappingException, IOException {
        System.out.println("processData()");
        final Betting betting = jsonProcessor.readJson(inputPath, Betting.class);
        final ArbitrageMock arbitrage = buildArbitrageModel(betting);
        jsonProcessor.writeJson(outputPath, arbitrage);
    }

    private ArbitrageMock buildArbitrageModel(final Betting betting) {
        System.out.println("buildArbitrageModel()");
        final Map<String, Match> matches = connectReferencesAndBuildMatches(betting);

        filterMatchesWithoutPair(matches);
        filterProfitableMatches(matches);

        final ArbitrageMock arbitrage = buildArbitrageMock(matches, betting.getBudget(), betting.getDesiredProfit());

        return arbitrage;
    }

    private ArbitrageMock buildArbitrageMock(final Map<String, Match> matches, final double budget, final double desiredProfit) {
        System.out.println("buildArbitrageMock()");
        final List<Match> matchesList = new ArrayList<Match>(matches.values());
        Collections.sort(matchesList, new MatchComparator());
        for (final Match m : matchesList) {
            System.out.println("VALUE: " + m.getProfit(budget));
        }
        final ArbitrageMock arbitrage = new ArbitrageMock();
        final double calculatedProfit = 0.0;
        final List<MatchMock> matchesMock = new ArrayList<MatchMock>();

        arbitrage.setCalculatedProfit(calculatedProfit);
        arbitrage.setMatches(matchesMock);
        return arbitrage;
    }

    private void filterMatchesWithoutPair(final Map<String, Match> matches) {
        final Iterator<Map.Entry<String, Match>> iter = matches.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry<String, Match> entry = iter.next();
            if (entry.getValue().getBetOffers().size() < Constants.MAX_NUM_OF_OUTCOMES) {
                System.out.println("removing match without pair - " + entry.getValue().getId());
                iter.remove();
            }
        }
    }

    private void filterProfitableMatches(final Map<String, Match> matches) {
        final Iterator<Map.Entry<String, Match>> iter = matches.entrySet().iterator();
        while (iter.hasNext()) {
            final Map.Entry<String, Match> entry = iter.next();

            if (entry.getValue().getArbitragePercentage() > 100.0) {
                System.out.println("removing unprofitable match - " + entry.getValue().getId() + " PERCENTAGE: "
                        + entry.getValue().getArbitragePercentage());
                iter.remove();
            }
        }
    }

    private Map<String, Match> connectReferencesAndBuildMatches(final Betting betting) {
        final Map<String, Match> matches = new HashMap<String, Match>();
        for (final Bookie bookie : betting.getBookies()) {
            bookie.setBetting(betting);
            for (final BetOffer offer : bookie.getBetOffers()) {
                offer.setBookie(bookie);
                if (matches.containsKey(offer.getId())) {
                    final Match m = matches.get(offer.getId());
                    m.getBetOffers().add(offer);
                } else {
                    final List<BetOffer> offersSet = new ArrayList<BetOffer>();
                    offersSet.add(offer);
                    final Match m = new Match(offer.getId(), offer.getName(), offersSet);
                    matches.put(m.getId(), m);
                }
            }
        }
        return matches;
    }
}
