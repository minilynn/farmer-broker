/**
 * 
 */
package com.gul.farmerbroker.goods.dos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gul.farmerbroker.goods.domain.Goods;

/**
 * @author Lynn
 *
 */
@Repository
public class GoodsRepositoryImpl implements IGoodsRepository {

	@Override
	public List<Goods> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Goods goods) {
		// TODO Auto-generated method stub

	}
}
