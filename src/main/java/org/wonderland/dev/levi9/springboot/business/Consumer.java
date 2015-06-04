package org.wonderland.dev.levi9.springboot.business;

import org.wonderland.dev.levi9.springboot.ClientPOSTRequest;
import org.wonderland.dev.levi9.springboot.engine.input.Betting;

public interface Consumer {
	public Betting consume(ClientPOSTRequest req);
}
