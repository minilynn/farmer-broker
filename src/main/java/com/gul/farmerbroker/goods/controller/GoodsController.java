/**
 * 
 */
package com.gul.farmerbroker.goods.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gul.farmerbroker.common.BaseAction;
import com.gul.farmerbroker.common.BaseModel;
import com.gul.farmerbroker.common.BusinessException;
import com.gul.farmerbroker.configuration.PropertiesHolder;
import com.gul.farmerbroker.goods.domain.Goods;
import com.gul.farmerbroker.goods.dos.IGoodsRepository;
import com.gul.farmerbroker.utils.ImageUtil;

/**
 * 商品服务Controller
 * 
 * @author Lynn
 */
@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseAction {
	private final static Logger logger = LoggerFactory.getLogger(GoodsController.class);

	/** 商品服务DOS对象 */
	@Autowired
	private IGoodsRepository goodsRep;

	@Value("${app.pageSize:null}")
	private Integer pageSize;

	@Value("${image.path:static/images}")
	private String imgPath;

	/**
	 * 检索所有商品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, produces = "application/hal+json")
	public Resources<Resource> findAllGoods(@RequestParam(name = "startId", required = false) Long id,
			@RequestParam(name = "pageSize", required = false) Integer pageSize) throws Exception {
		Goods reqGoods = new Goods();
		if (id != null) {
			reqGoods.setId(id);
		}
		if (pageSize != null) {
			this.pageSize = pageSize;
		}
		reqGoods.setPageSize(pageSize);

		Iterable<Goods> result = goodsRep.findAll(reqGoods);
		List<BaseModel> rsList = new ArrayList<>();
		if (result != null) {
			result.forEach(goods -> rsList.add(goods));
		}

		return genResultList(rsList);
	}

	/**
	 * 检索农场主的所有商品列表
	 * 
	 * @param id
	 *            农场主编号
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(path = "/farmer/{id}", method = RequestMethod.GET, produces = "application/hal+json")
	public Resources<Resource> findFarmerGoods(@PathVariable Integer id) throws Exception {
		Goods reqGoods = new Goods();
		if (id != null) {
			reqGoods.setFarmerId(id);
		}

		Iterable<Goods> result = goodsRep.findByFarmerId(reqGoods);
		List<BaseModel> rsList = new ArrayList<>();
		if (result != null) {
			result.forEach(goods -> rsList.add(goods));
		}
		return genResultList(rsList);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/hal+json")
	public ResponseEntity<?> addGoods(@RequestBody Goods goods) {
		if (logger.isDebugEnabled()) {
			logger.debug("新增商品信息：" + goods.getName());
		}

		goods.setNew(true);

		if (StringUtils.isBlank(goods.getType())) {
			return badRequest("商品类型不能为空！");
		}
		goodsRep.save(goods);

		return null;
	}

	/**
	 * 上传图片，图片文件的Key名为image-file
	 * 
	 * @param multipartFile
	 * @return
	 * @throws Exception
	 */
	// @RequestMapping(path = "/image", method = RequestMethod.POST, produces =
	// "application/hal+json")
	@PostMapping(path = "/image", produces = "application/hal+json")
	public ResponseEntity<?> uploadImg(@RequestParam("image-file") MultipartFile multipartFile,
			HttpServletRequest request) throws Exception {
		if (multipartFile == null || multipartFile.isEmpty()
				|| StringUtils.isBlank(multipartFile.getOriginalFilename())) {
			throw new BusinessException(PropertiesHolder.getPropertyValue("err.goods.image.absent"));
		}

		String contentType = multipartFile.getContentType();
		if (!contentType.contains("")) {
			throw new BusinessException(PropertiesHolder.getPropertyValue("err.goods.image.format"));
		}

		String orgFileName = multipartFile.getOriginalFilename();
		logger.info("上传图片:name={},type={}", orgFileName, contentType);
		// 处理图片
		// User currentUser = userService.getCurrentUser();
		// 获取路径
		String sufPath = ImageUtil.getFileName("", orgFileName);
		String filePath = imgPath + sufPath;
		logger.info("图片保存路径={}", filePath);
		String file_name = null;
		// try {
		// file_name = ImageUtil.saveImg(multipartFile, filePath);
		// MarkDVo markDVo = new MarkDVo();
		// markDVo.setSuccess(0);
		// if (StringUtils.isNotBlank(file_name)) {
		// markDVo.setSuccess(1);
		// markDVo.setMessage("上传成功");
		// markDVo.setUrl(sufPath + File.separator + file_name);
		// markDVo.setCallback(callback);
		// }
		// logger.info("返回值：{}", markDVo);
		// return markDVo;
		// } catch (IOException e) {
		// throw new BusinessException(ResultEnum.SAVE_IMG_ERROE);
		// }

		return null;
	}

	/**
	 * 请求数据不合法或者不完整时，调用该方法构造相应报文，返回错误信息，以及相应状态码400
	 * 
	 * @param msg
	 *            错误信息
	 * @return
	 */
	private ResponseEntity<?> badRequest(String msg) {
		ResponseEntity<?> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		return response;
	}

	/**
	 * 添加个性化子资源URI
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Resources<Resource> genResultList(List<BaseModel> entities) {
		Resources<Resource> resList = super.genResultList(entities);
		for (Resource res : resList) {
			Goods goods = (Goods) res.getContent();
			Link farmerLink = linkTo(getClass()).slash("farmer/" + goods.getFarmerId()).withRel("farmer_goods");
			res.add(farmerLink);
		}
		return resList;
	}
}
