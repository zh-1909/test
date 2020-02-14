package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.UpdateException;
/**
 * 处理收货地址数据的业务层实现类
 * @author tarena
 *
 */
@Service
public class AddressServiceImpl implements IAddressService{
	
	@Autowired
	private AddressMapper mapper;
	
	@Autowired
	private IDistrictService districtService;
	
	@Value("${project.address-max-count}")
	private int count;
	/**
	 * 新增收货地址
	 * @param uid 用户的id
	 * @param username 用户名
	 * @param address 收货地址数据
	 */
	@Override
	public void addnew(Integer uid, String username, Address address) {
		//根据uid统计该用户的收货数量
		Integer counts = mapper.countByUid(uid);
		System.err.println(count);
		//判断数量是超出设置
		if(counts>=count){
			throw new AddressCountLimitException("增加收货地址失败！收货地址数量已经达到上限("+ count + ")！");
		}
		
		//根据以上统计数量是否为0,得到isDefault值
		Integer isDefault = counts == 0? 1:0;
		//创建当前时间对象now
		Date now = new Date();
		 
		// 补全数据：省市区的名称
		String provinceCode = address.getProvinceCode();
		String provinceName = districtService.getNameByCode(provinceCode);
		address.setProvinceName(provinceName);
		
		String cityCode = address.getCityCode();
		String cityName = districtService.getNameByCode(cityCode);
		address.setCityName(cityName);
		
		String areaCode = address.getAreaCode();
		String areaName = districtService.getNameByCode(areaCode);
		address.setAreaName(areaName);
		
		address.setUid(uid);
		//补全数据:is_defult
		address.setIsDefault(isDefault);
		//补全数据:4项日志(username,now)
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedTime(now);
		address.setModifiedUser(username);
		insert(address);
//		//执行插入用户数据,并获取返回的受影响行数
//		Integer rows = mapper.insert(address);
//		//判断受影响行数是否不为1
//		if(rows!=1) {
//			//是:抛出InsertException
//			throw new InsertException("增加收货地址失败！插入收货地址数据时出现未知错误，请联系系统管理员！");
//		}
	}
	
	/**
	 * 查询uid的收货地址列表
	 */
	@Override
	public List<Address> getByUid(Integer uid) {
		List<Address> list = mapper.findByUid(uid);
		for (Address address : list) {
			address.setUid(null);
			address.setProvinceCode(null);
			address.setCityCode(null);
			address.setAreaCode(null);
			address.setCreatedTime(null);
			address.setCreatedUser(null);
			address.setModifiedTime(null);
			address.setModifiedUser(null);
			address.setIsDefault(null);
		}
		return list;
	}
	@Transactional
	@Override
	public void setDefault(Integer aid, Integer uid, String username) {
		//根据参数aid查询收货地址数据
		Address result = mapper.findByAid(aid);
		//判断是查询结果是否为null
		if(result==null) {
			// 是：AddressNotFoundException
			throw new AddressNotFoundException("设置默认收货地址失败！尝试访问的数据不存在！");
		}
		
		//判断查询结果中的uid与参数uid是不是一直
		if(!result.getUid().equals(uid)){
			// 是：AccessDeniedException
			throw new AccessDeniedException("设置默认收货地址失败！非法访问已经被拒绝！");
		}
		
		/// 将该用户的所有收货地址设置为非默认，并获取返回值
		Integer in =mapper.updateNonDefaultByUId(uid);
		// 判断返回值是否小于1
		if(in<1) {
			// 是：UpdateException
			throw new UpdateException("设置默认收货地址失败[1]！更新收货地址数据时出现未知错误，请联系系统管理员！");
		}
		
		// 将指定的收货地址设置为默认，并获取返回值
		Date modifiedTime = new Date();
		String modifiedUser = username;
		Integer rows = mapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
		// 判断返回值是否不为1
		if(rows!=1) {
			//是：UpdateException
			throw new UpdateException("设置默认收货地址失败[2]！更新收货地址数据时出现未知错误，请联系系统管理员！");
		}
		
		

		
	}
	@Transactional
	@Override
	public void delete(Integer aid, Integer uid, String username) {
		//根据aid查询收货地址数据
		Address result = mapper.findByAid(aid);
		//判断是查询结果否为null
		if(result==null) {
			//是:抛出AddressNotFoundException
			throw new AddressNotFoundException("查询用户收货地址不存在");
		}
		
		//判断查询结果中的uid与参数uid是否不同
		if(!result.getUid().equals(uid)) {
			//抛出:AccessDeniedException
			throw new AccessDeniedException("modified_time非法访问已经被拒绝！");
		}
		
		deleteByAid(aid);
//		//执行删除,并获取返回值
//		Integer rows = mapper.deleteByAid(aid);
//		//判断
//		if(rows!=1) {
//			throw new DeleteException("删除失败,联系系统管员");
//			
//		}
		
		//判断查询的结果(对应的删除数的数据)中的isDefaule是否不为1
		if(result.getIsDefault()!=1) {
			return;
		}
		
		//统计当前用户还有多少收货地址
		countByUid(uid);
		//统计当前用户还有多少收货地址
//		Integer count =mapper.countByUid(uid);
//		if(count==0) {
//			return;
//		}
		
		// 查询当前用户的最后修改的那一条收货地址
		Address address = mapper.findLastModifiedByUid(uid);
		// 从本次查询中取出数据的aid
		 aid=address.getAid();
		 updateDefaultByAid(aid,username,new Date());
//		// 执行设置默认收货地址，获取返回值
//		Integer lastModifiedByAid = mapper.updateDefaultByAid(address.getAid(),username,new Date());
//		// 判断返回值是否不为1
//		if(lastModifiedByAid!=1) {
//			// 是：UpdateException
//			throw new UpdateException("设置默认收货地址失败[2]！更新收货地址数据时出现未知错误，请联系系统管理员！");
//		}
				
		
	}
	
	
	/**
	 * 插入收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	private Integer insert(Address address) {

		//执行插入用户数据,并获取返回的受影响行数
		Integer rows = mapper.insert(address);
		//判断受影响行数是否不为1
		if(rows!=1) {
			//是:抛出InsertException
			throw new InsertException("增加收货地址失败！插入收货地址数据时出现未知错误，请联系系统管理员！");
		}
		return rows; 
	}
	
	/**
	 * 删除货地址id查询收货地址是否存在
	 * @param aid 收货地址
	 * @return 行数
	 */
	private Integer deleteByAid(Integer aid) {
		//执行删除,并获取返回值
		Integer rows = mapper.deleteByAid(aid);
		//判断
		if(rows!=1) {
			throw new DeleteException("删除失败,联系系统管员");

		}
		return rows;
	}
	
	/**
	 * 
	 * @param uid 用户的id
	 * @return 该用户的收货地址数据的数量
	 */
	private Integer updateNonDefaultByUId(Integer uid) {
		return mapper.updateNonDefaultByUId(uid);
		}
	
	/**
	 * 将指定的收货地址设置为默认
	 * @param aid
	 * @param modifiedUser
	 * @param modifiedTime
	 */
	private void updateDefaultByAid(Integer aid,String modifiedUser,Date modifiedTime) {
		// 将指定的收货地址设置为默认
		Integer rows = mapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
		// 判断返回值是否不为1
		if(rows!=1) {
			throw new UpdateException("设置默认收货地址失败[2]！更新收货地址数据时出现未知错误，请联系系统管理员！");
		}
		
	}
	
	/**
	 * 统计某用户的收货地址数据的数量
	 * @param uid 用户的id
	 * @return 该用户的收货地址数据的数量
	 */
	private Integer countByUid(Integer uid) {
		Integer counts = mapper.countByUid(uid);
		System.err.println(count);
		//判断数量是超出设置
		if(counts>=count){
			throw new AddressCountLimitException("增加收货地址失败！收货地址数量已经达到上限("+ count + ")！");
		}
		if(counts==0) {
			return counts;
		}
		return counts;
	}

	@Override
	public Address getByAid(Integer aid, Integer uid) {
		// 根据参数aid查询收货地址数据
				Address result = findByAid(aid);
				// 判断查询结果是否为null
				if (result == null) {
					// 是：AddressNotFoundException
					throw new AddressNotFoundException(
						"查询收货地址失败！尝试访问的数据不存在！");
				}

				// 判断查询结果中的uid与参数uid是否不一致
				if (!result.getUid().equals(uid)) {
					// 是：AccessDeniedException
					throw new AccessDeniedException(
						"查询收货地址失败！非法访问已经被拒绝！");
				}
				
				// 将查询结果中的某些属性设置为null
				result.setUid(null);
				result.setProvinceCode(null);
				result.setCityCode(null);
				result.setAreaCode(null);
				result.setIsDefault(null);
				result.setCreatedUser(null);
				result.setCreatedTime(null);
				result.setModifiedUser(null);
				result.setModifiedTime(null);
				
				// 返回查询结果
				return result;
	}
	
	private Address findByAid(Integer aid) {
		return mapper.findByAid(aid);
	}
	
}






