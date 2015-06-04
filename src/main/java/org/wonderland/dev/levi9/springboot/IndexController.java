package org.wonderland.dev.levi9.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wonderland.dev.levi9.springboot.business.Consumer;
import org.wonderland.dev.levi9.springboot.business.MainConsumer;
import org.wonderland.dev.levi9.springboot.engine.output.mock.ArbitrageMock;

@RestController
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<ArbitrageMock> index(@RequestBody ClientPOSTRequest input) {
		Consumer consumer = new MainConsumer();
		System.out.println(consumer.consume(input));
		//System.out.println(input);
		return new ResponseEntity<ArbitrageMock>(new ArbitrageMock(), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ArbitrageMock index(Model model) {
		return new ArbitrageMock();
	}
}
