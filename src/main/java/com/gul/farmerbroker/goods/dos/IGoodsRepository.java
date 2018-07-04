/**
 * 
 */
package com.gul.farmerbroker.goods.dos;

import java.util.List;

import com.gul.farmerbroker.common.BusinessException;
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

	/**
	 * 根据农场主编号查询所有的商品信息列表
	 * 
	 * @param goods
	 * @return
	 * @throws BusinessException
	 *             输入参数为空，或者参数对象中的农场主ID为空时抛出
	 */
	List<Goods> findByFarmerId(Goods goods) throws BusinessException;

	void save(Goods goods);
}
