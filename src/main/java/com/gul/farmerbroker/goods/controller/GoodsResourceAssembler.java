package com.gul.farmerbroker.goods.controller;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.gul.farmerbroker.common.BaseModel;
import com.gul.farmerbroker.goods.resource.GoodsResource;

public class GoodsResourceAssembler extends ResourceAssemblerSupport<BaseModel, GoodsResource> {
	public GoodsResourceAssembler(Class<?> sourceClass) {
		super(sourceClass, GoodsResource.class);
	}

	public GoodsResource toResource(BaseModel entity) {
		GoodsResource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}

	protected GoodsResource instantiateResource(BaseModel entity) {
		return new GoodsResource(entity);
	}
}
