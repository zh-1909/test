package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Product;

/**
 * 处理商品数量业务持久层
 * @author tarena
 *
 */
public interface IProductService {

	/**
	 * 查询热销商品
	 */
	List<Product> getHotList();
	
	/**
	 * 获取产品信息
	 * @param id
	 * @return
	 */
	Product getById(Integer id);
}
