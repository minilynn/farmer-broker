/**
 * 
 */
package com.gul.farmerbroker.goods.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;

import com.gul.farmerbroker.common.BaseModel;

/**
 *
 * @author Lynn
 */
public class GoodsResource extends Resource {
	public GoodsResource(BaseModel book) {
		super(book);
		Long bookId = book.getId();
		add(linkTo(getClass()).slash(book).withRel("items"));
	}
}
