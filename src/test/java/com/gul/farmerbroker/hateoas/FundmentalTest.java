/**
 * 
 */
package com.gul.farmerbroker.hateoas;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.core.ControllerEntityLinksFactoryBean;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import com.gul.farmerbroker.hateoas.base.Person;
import com.gul.farmerbroker.hateoas.base.PersonController;
import com.gul.farmerbroker.hateoas.base.PersonResource;

/**
 *
 * @author Lynn
 *
 */
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootTest
public class FundmentalTest {
	@Test
	public void testLinks() {
		Link link = new Link("http://localhost:8080");
		assertThat(link.getHref(), is("http://localhost:8080"));
		assertThat(link.getRel(), is(Link.REL_SELF));

		link = new Link("http://localhost:8080", "my-rel");
		assertThat(link.getHref(), is("http://localhost:8080"));
		assertThat(link.getRel(), is("my-rel"));
	}

	@Test
	public void testResource() {
		PersonResource pr = new PersonResource();
		pr.setFirstName("Lynn");
		pr.setLastName("Gu");
		pr.add(new Link("http://localhost:8080/lynngu"));

		Link selfLink = new Link("http://localhost:8080/lynngu");
		assertThat(pr.getId(), is(selfLink));
		assertThat(pr.getLink(Link.REL_SELF), is(selfLink));
	}

	@Test
	public void testController() {
		Link link = ControllerLinkBuilder.linkTo(PersonController.class).withRel("people");
		assertThat(link.getRel(), is("people"));
		assertThat(link.getHref(), endsWith("/people"));

		Person person = new Person(1, "Dave", "Matthews");
		// /person / 1
		link = ControllerLinkBuilder.linkTo(PersonController.class).slash(person.getId()).withSelfRel();
		assertThat(link.getRel(), is(Link.REL_SELF));
		assertThat(link.getHref(), endsWith("/people/1"));

		link = ControllerLinkBuilder.linkTo(PersonController.class).slash(person).withSelfRel();
		assertThat(link.getRel(), is(Link.REL_SELF));
		assertThat(link.getHref(), endsWith("/people/1"));
	}

	@Test
	public void testBindUri() {
		try {
			Method method = PersonController.class.getMethod("show", Long.class);
			Link link = ControllerLinkBuilder.linkTo(method, 2L).withSelfRel();
			assertThat(link.getHref(), endsWith("/people/2"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Link link = linkTo(methodOn(PersonController.class).show(2L)).withSelfRel();
		assertThat(link.getHref(), endsWith("/people/2"));
	}
	
	@Test
	public void testEntityLink() {
		// try {
		// EntityLinks links = new ControllerEntityLinksFactoryBean().getObject();
		// LinkBuilder builder = links.linkFor(PersonResource.class);
		// Link link = links.linkToSingleResource(PersonResource.class, 1L);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}
}
