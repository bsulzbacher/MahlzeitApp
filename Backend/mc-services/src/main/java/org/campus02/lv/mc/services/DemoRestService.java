package org.campus02.lv.mc.services;

import java.util.concurrent.atomic.AtomicLong;

import org.campus02.lv.mc.pojo.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoRestService {

	private static final Logger log_ = LoggerFactory.getLogger(DemoRestService.class);

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping(method = RequestMethod.GET, path = "/greeting/{name}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Greeting greeting(@PathVariable(value = "name") String name) {
		log_.info("greeting({})", name);
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/greeting")
	public void greeting(@RequestBody Greeting greeting) {
		log_.info("greeting({})", greeting);
	}
}


/*
 * @ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order") // 404
 * public class OrderNotFoundException extends RuntimeException { // ... }
 */