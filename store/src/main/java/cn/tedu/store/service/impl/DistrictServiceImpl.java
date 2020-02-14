package cn.tedu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.DistrictMapper;
import cn.tedu.store.service.IDistrictService;
@Service
public class DistrictServiceImpl implements IDistrictService{
	
	@Autowired
	private DistrictMapper mapper;
	
	@Override
	public List<District> getByParent(String parent) {
		 List<District> list= mapper.findByParent(parent);
		 for (District district : list) {
			 district.setId(null);
			 district.setParent(null);
			 System.err.println(district);
		 }
		return list;
	}
	
	/**
	 * 省市区-根据行政代号获取名称-业务
	 * @param code 代号
	 * @return
	 */
	@Override
	public String getNameByCode(String code) {
		String name = mapper.findNameByCode(code);
		
		return name;
	}

}
