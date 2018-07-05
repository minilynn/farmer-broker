package com.gul.farmerbroker.common;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class SimpleResourceAssembler extends ResourceAssemblerSupport<BaseModel, Resource> {
	public SimpleResourceAssembler(Class<?> sourceClass) {
		super(sourceClass, Resource.class);
	}

	public Resource<BaseModel> toResource(BaseModel entity) {
		Resource resource = createResourceWithId(entity.getId(), entity);
		return resource;
	}

	protected Resource instantiateResource(BaseModel entity) {
		return new Resource(entity);
	}
}
