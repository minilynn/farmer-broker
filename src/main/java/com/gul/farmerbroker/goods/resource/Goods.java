/**
 * 
 */
package com.gul.farmerbroker.goods.resource;

import java.sql.Timestamp;

import com.gul.farmerbroker.common.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Lynn
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods extends BaseModel {
	/** 商品编号，使用父类的ID信息 */
	// private Long id;
	/** 商品类型，10001-有机蔬菜，10002-有机水果，00002--水果，10003--有机肉类，10004--有机鱼类 */
	private String type;
	/** 商品状态，0-无效商品，1-新建待审核，2-代理商审批通过，待终审，3-审批通过，待出售，4-暂停出售，5-已售罄，6-已删除 */
	private Integer status;
	/** 销售单位，1-个，2-斤 */
	private Integer unitType;
	/** 商品单价 */
	private Float price;
	/** 商品库存量 */
	private Integer unitCount;
	/** 商品名称 */
	private String name;
	/** 商品所在区域 */
	private String area;
	/** 商品所属农场主编号 */
	private Integer farmerId;
	/** 此刻农场主星级 */
	private Integer farmerRank;
	/** 商品创建时间 */
	private Timestamp createTime;
	/** 商品有效日期，数值大小为YYYYMMDD */
	private Integer availableTill;
	/** 商品描述信息 */
	private String description;
}
