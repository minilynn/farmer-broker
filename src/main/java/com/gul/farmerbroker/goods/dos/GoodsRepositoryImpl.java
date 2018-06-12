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
	
	@Override
	public List<Goods> findAll() {
		return gmapper.findAll();
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
