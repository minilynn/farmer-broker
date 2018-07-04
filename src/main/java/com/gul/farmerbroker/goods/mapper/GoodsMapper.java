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
	 * 
	 * @param goods
	 * @return
	 */
	List<Goods> findAll(Goods goods);

	/**
	 * 根据农场主编号查询其所有商品信息
	 * 
	 * @param goods
	 * @return
	 */
	List<Goods> findByFarmerId(Goods goods);

	void insert(Goods goods);
}
