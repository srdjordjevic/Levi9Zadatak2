package org.wonderland.dev.levi9.springboot.multiengine;

import org.wonderland.dev.levi9.springboot.engine.input.Betting;
import org.wonderland.dev.levi9.springboot.engine.output.mock.ArbitrageMock;

public class Engine {

	public ArbitrageMock findSafeBets(Betting betting) {
		ArbitrageMock mock = new ArbitrageMock();
		
		return mock;
	}
	
}
