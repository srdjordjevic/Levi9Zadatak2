package org.wonderland.dev.levi9.json.pdnj.model.output.mock;

public abstract class PlacedBetMock {
	
	private String bookieID;

	public PlacedBetMock(String bookieID) {
		this.bookieID = bookieID;
	}
	
	public String getBookieID() {
		return bookieID;
	}

	public void setBookieID(String bookieID) {
		this.bookieID = bookieID;
	}
	
}	
