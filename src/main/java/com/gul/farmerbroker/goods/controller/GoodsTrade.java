/**
 * 
 */
package com.gul.farmerbroker.goods.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gul.farmerbroker.common.BaseModel;
import com.gul.farmerbroker.goods.domain.Goods;
import com.gul.farmerbroker.goods.dos.IGoodsRepository;
import com.gul.farmerbroker.goods.resource.GoodsResource;

/**
 *
 * @author Lynn
 *
 */
@RestController("goods")
public class GoodsTrade extends GoodsBaseAction {
	private final static Logger logger = LoggerFactory.getLogger(GoodsTrade.class);

	@Autowired
	private IGoodsRepository goodsRep;

	// 检索所有书本
	@RequestMapping(method = RequestMethod.GET, produces = "application/hal+json")
	public Resources<GoodsResource> findAllGoods() throws Exception {
		Iterable<Goods> result = goodsRep.findAll();
		List<BaseModel> rsList = new ArrayList<>();
		if (result != null) {
			result.forEach(goods -> rsList.add(goods));
		}
		return genResultList(rsList);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/hal+json")
	public ResponseEntity<?> addGoods(@RequestBody Goods goods) {
		if (logger.isDebugEnabled()) {
			logger.debug("新增商品信息：" + goods.getName());
		}

		goods.setNew(true);

		if (StringUtils.isBlank(goods.getType())) {
			return badRequest("商品类型不能为空！");
		}
		goodsRep.save(goods);

		return null;
	}

	/**
	 * 请求数据不合法或者不完整时，调用该方法构造相应报文，返回错误信息，以及相应状态码400
	 * 
	 * @param msg
	 *            错误信息
	 * @return
	 */
	private ResponseEntity<?> badRequest(String msg) {
		ResponseEntity<?> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		return response;
	}
}
