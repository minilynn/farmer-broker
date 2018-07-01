/**
 * 
 */
package com.gul.farmerbroker.goods.mapper;

import java.util.List;

import com.gul.farmerbroker.goods.domain.Goods;

/**
 * @author Lynn
 */
public interface GoodsMapper {
	/**
	 * 根据商品ID的翻页查询
	 * @param goods
	 * @return
	 */
	List<Goods> findAll(Goods goods);
	void insert(Goods goods);
}
