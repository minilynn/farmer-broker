/**
 * 
 */
package com.gul.farmerbroker.goods.dos;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gul.farmerbroker.goods.domain.Goods;
import com.gul.farmerbroker.goods.mapper.GoodsMapper;

/**
 * @author Lynn
 */
@Repository
public class GoodsRepositoryImpl implements IGoodsRepository, InitializingBean {
	@Autowired
	private SqlSession sqlSessOion;
	private GoodsMapper gmapper;

	/**
	 * 根据商品ID的翻页查询，商品ID为bigint，设置的允许最大值为12个9，每页默认记录数为20
	 * 
	 * @see com.gul.farmerbroker.goods.dos.IGoodsRepository#findAll(com.gul.farmerbroker.goods.domain.Goods)
	 */
	@Override
	public List<Goods> findAll(Goods goods) {
		if (goods == null) {
			goods = new Goods();
		}
		if (goods.getId() == null) {
			goods.setId(999999999999L);
		}
		if (goods.getPageSize() == null) {
			goods.setPageSize(20);
		}
		return gmapper.findAll(goods);
	}

	@Override
	public void save(Goods goods) {
		gmapper.insert(goods);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		gmapper = sqlSessOion.getMapper(GoodsMapper.class);
	}
}
