/**
 * 
 */
package com.gul.farmerbroker.goods.dos;

import java.util.List;

import com.gul.farmerbroker.goods.domain.Goods;

/**
 * @author Lynn
 */
public interface IGoodsRepository {
	/**
	 * 根据商品ID的翻页查询，商品ID为bigint，设置的允许最大值为12个9
	 * 
	 * @param goods
	 * @return
	 */
	List<Goods> findAll(Goods goods);

	void save(Goods goods);
}
