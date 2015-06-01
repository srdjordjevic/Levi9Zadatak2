package org.wonderland.dev.levi9.springboot.engine.output.mock;

import java.util.List;

public class MatchMock {
	private String id;
	private String name;
	private List<PlacedBetMock> placedBets;
	
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
	public List<PlacedBetMock> getPlacedBets() {
		return placedBets;
	}
	public void setPlacedBets(List<PlacedBetMock> placedBets) {
		this.placedBets = placedBets;
	}
	
	
}
