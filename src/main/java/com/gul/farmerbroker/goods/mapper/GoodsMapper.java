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
	List<Goods> findAll();
	void insert(Goods goods);
}
