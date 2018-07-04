package com.gul.farmerbroker.goods.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;

import com.gul.farmerbroker.common.BaseModel;
import com.gul.farmerbroker.goods.domain.Goods;
import com.gul.farmerbroker.goods.resource.GoodsResource;

public class GoodsBaseAction {
	@Autowired
	EntityLinks entityLinks;

	// 构建单个资源对象
	public Resource<?> genResultListByCode(BaseModel entity) {
		return new GoodsResourceAssembler(getClass()).toResource(entity);

	}

	// 构建多个资源对象
	public Resources<GoodsResource> genResultList(List<BaseModel> entities) {
		Link selfLink = linkTo(getClass()).withSelfRel();
		List<GoodsResource> resList = new GoodsResourceAssembler(getClass()).toResources(entities);
		for (GoodsResource res : resList) {
			Goods goods = (Goods) res.getContent();
			Link farmerLink = linkTo(getClass()).slash("farmer/" + goods.getFarmerId()).withRel("farmer_goods");
			res.add(farmerLink);
		}
		return new Resources<>(resList, selfLink);
	}

	// 返回请求头的信息
	public HttpHeaders genHeaders(BaseModel entity) {
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(entityLinks.linkForSingleResource(BaseModel.class, entity).toUri());
		return headers;
	}
}
