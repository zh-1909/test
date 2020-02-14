package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Product;
import cn.tedu.store.mapper.ProductMapper;
import cn.tedu.store.service.IProductService;
import cn.tedu.store.service.ex.ProductNotFoundException;
@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private ProductMapper mapper;
	@Override
	public List<Product> getHotList() {
		List<Product> list = findHotList();
		for (Product product : list) {
			product.setCategoryId(null);
			product.setItemType(null);
			product.setSellPoint(null);
			product.setNum(null);
			product.setStatus(null);
			product.setPriority(null);
			product.setCreatedUser(null);
			product.setCreatedTime(null);
			product.setModifiedUser(null);
			product.setModifiedTime(null);
		}
		return  list;
	} 
	/**
	 * 根据商品id查询该商品数据
	 */
	@Override
	public Product getById(Integer id) {
		Product  product = findById(id);
		if(product==null) {
			throw new ProductNotFoundException("getById(Integer id):查询数据不存在") ;
		}
		product.setCategoryId(null);
		product.setCreatedUser(null);
		product.setCreatedTime(null);
		product.setModifiedUser(null);
		product.setModifiedTime(null);
		return product;
	}
	 
	/**
	 * 查询热销商品
	 * @return 热销商品列表
	 */
	private List<Product> findHotList(){
		return mapper.findHotList();
	}


	/**
	 * 根据商品id查询该商品数据
	 * @param id
	 * @return
	 */
	private Product findById(Integer id) {
	
		return mapper.findById(id);
	}
	
}
