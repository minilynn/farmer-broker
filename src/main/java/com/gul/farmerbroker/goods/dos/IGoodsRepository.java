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
	List<Goods> findAll();

	void save(Goods goods);
}
