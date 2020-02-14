package cn.tedu.store.mapper;

import java.util.List;

import cn.tedu.store.entity.Product;

public interface ProductMapper {
	/**
	 * 查询热销商品                   
	 * @return 热销商品列表
	 */
	List<Product> findHotList();
	
	/**
	 * 根据商品id查询该商品数据
	 * @param id
	 * @return
	 */
	Product findById(Integer id);
}
