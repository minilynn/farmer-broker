/**
 * 
 */
package com.gul.farmerbroker.goods.repository;

import org.springframework.data.repository.CrudRepository;

import com.gul.farmerbroker.goods.resource.Goods;

/**
 * @author Lynn
 */
public interface GoodsRepository extends CrudRepository<Goods, Long> {
}
