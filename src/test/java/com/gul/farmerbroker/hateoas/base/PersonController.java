/**
 * 
 */
package com.gul.farmerbroker.hateoas.base;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Lynn
 *
 */
@Controller
@RequestMapping("/people")
public class PersonController {
	@RequestMapping(method = RequestMethod.GET)
	public HttpEntity<PersonResource> showAll() {
		return null;
	}

	@RequestMapping(value = "{person}", method = RequestMethod.GET)
	public HttpEntity<PersonResource> show(@PathVariable Long person) {
		return null;
	}
}
