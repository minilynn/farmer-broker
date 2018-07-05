package com.gul.farmerbroker.common;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;

public class BaseAction {
	@Autowired
	EntityLinks entityLinks;

	// 构建单个资源对象
	public Resource<?> genResultListByCode(BaseModel entity) {
		return new SimpleResourceAssembler(getClass()).toResource(entity);

	}

	/**
	 * 构建集合资源对象，可重新该方法，在获取集合资源信息对象后，为其中的每个资源添加链接信息
	 * 
	 * @param entities
	 * @return 集合资源信息
	 */
	@SuppressWarnings("rawtypes")
	public Resources<Resource> genResultList(List<BaseModel> entities) {
		Link selfLink = linkTo(getClass()).withSelfRel();
		List<Resource> resList = new SimpleResourceAssembler(getClass()).toResources(entities);
		return new Resources<>(resList, selfLink);
	}

	// 返回请求头的信息
	public HttpHeaders genHeaders(BaseModel entity) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(entityLinks.linkForSingleResource(BaseModel.class, entity).toUri());
		return headers;
	}
}
